package com.yun.wiretransferservice.adapter.out.persistence;

import com.yun.wiretransferservice.domain.WireTransfer;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;
import org.springframework.stereotype.Component;

import static com.yun.wiretransferservice.domain.WireTransfer.*;

@Component
public class WireTransferMapper {
    public WireTransfer mapToDomainEntity(WireTransferEntity wireTransferEntity) {
        return generatedWireTransfer(
                new WireTransferRequestId(wireTransferEntity.getRequestId()),
                new FromMembershipId(wireTransferEntity.getFromMembershipId()),
                new ToMembershipId(wireTransferEntity.getToMembershipId()),
                new ToBankName(wireTransferEntity.getToBankName()),
                new ToBankAccountNumber(wireTransferEntity.getToBankAccountNumber()),
                new WireTransferAmount(wireTransferEntity.getWireTransferAmount()),
                new IsValid(wireTransferEntity.isValid())
        );
    }

    public WireTransferEntity toEntity(WireTransferRequestDomain wireTransferRequestDomain) {
        return WireTransferEntity.of(
                null,
                wireTransferRequestDomain.getWireTransferRequestId(),
                wireTransferRequestDomain.getFromMembershipId(),
                wireTransferRequestDomain.getToMembershipId(),
                wireTransferRequestDomain.getToBankName(),
                wireTransferRequestDomain.getToBankAccountNumber(),
                wireTransferRequestDomain.getWireTransferAmount(),
                true
        );
    }
}
