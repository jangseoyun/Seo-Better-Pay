package com.yun.banking.adapter.out.persistence;

import com.yun.banking.domain.TransferFirmBanking;
import org.springframework.stereotype.Component;

import static com.yun.banking.domain.TransferFirmBanking.*;

@Component
public class TransferFirmBankingMapper {
    public TransferFirmBanking mapToDomainEntity(TransferFirmBankingEntity transferFirmBankingEntity) {
        return generateTransferFirmBaking(
                new TransferFirmBankingId(transferFirmBankingEntity.getId() + ""),
                new FromBankName(transferFirmBankingEntity.getFromBankName()),
                new FromBankAccountNumber(transferFirmBankingEntity.getFromBankName()),
                new ToBankName(transferFirmBankingEntity.getToBankName()),
                new ToBankAccountNumber(transferFirmBankingEntity.getToBankAccountNumber()),
                new TransferAmount(transferFirmBankingEntity.getTransferAmount()),
                new TransferRequestStatus(transferFirmBankingEntity.getTransferRequestStatus())
        );
    }

}
