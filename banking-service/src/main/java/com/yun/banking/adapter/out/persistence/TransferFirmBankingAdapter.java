package com.yun.banking.adapter.out.persistence;

import com.yun.banking.application.port.out.TransferFirmBankingPort;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.banking.domain.TransferRequestStatusEnum;
import com.yun.banking.exception.BankingModuleException;
import com.yun.common.anotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.yun.banking.exception.BankingServiceErrorCode.ENTITY_NOT_FOUND;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class TransferFirmBankingAdapter implements TransferFirmBankingPort {

    private final TransferFirmBankingJpaRepository transferFirmBankingJpaRepository;
    private final TransferFirmBankingMapper mapper;

    @Override
    public TransferFirmBanking createdTransferBankingInfo(TransferFirmBanking transferFirmBanking) {
        TransferFirmBankingEntity successTransfer
                = transferFirmBankingJpaRepository.save(TransferFirmBankingEntity.of(transferFirmBanking));
        return mapper.mapToDomainEntity(successTransfer);
    }

    @Override
    public void processingTransferFirmBanking(TransferFirmBanking.TransferAggregateIdentifier firmBankingRequestAggregateIdentifier) {
        TransferFirmBankingEntity transferFirmBankingEntity = transferFirmBankingJpaRepository.findByTransferAggregateIdentifier(firmBankingRequestAggregateIdentifier.getAggregateIdentifier())
                .orElseThrow(() -> {
                    throw new BankingModuleException(ENTITY_NOT_FOUND, ENTITY_NOT_FOUND.getMessage());
                });

        transferFirmBankingEntity.succeedProcessingTransferFirmBanking(TransferRequestStatusEnum.SUCCESS);
        TransferFirmBankingEntity succeedFirmBankingEntity = transferFirmBankingJpaRepository.save(transferFirmBankingEntity);
        log.info("ProcessingTransferFirmBanking modify status: {}", succeedFirmBankingEntity);
    }
}
