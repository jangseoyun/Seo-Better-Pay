package com.yun.banking.adapter.out.persistence;

import com.yun.banking.application.port.out.TransferFirmBankingPort;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

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
}
