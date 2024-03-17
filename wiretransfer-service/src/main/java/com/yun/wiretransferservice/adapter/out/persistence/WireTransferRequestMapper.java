package com.yun.wiretransferservice.adapter.out.persistence;

import com.yun.wiretransferservice.adapter.in.web.model.WireTransferType;
import com.yun.wiretransferservice.domain.WireTransferRequestDomain;
import com.yun.wiretransferservice.domain.WireTransferRequestStatus;
import org.springframework.stereotype.Component;

import static com.yun.wiretransferservice.domain.WireTransferRequestDomain.*;

@Component
public class WireTransferRequestMapper {
    public WireTransferRequestDomain mapToDomainEntity(WireTransferRequestEntity wireTransferRequestEntity) {
        return generatedWireTransfer(
                new WireTransferRequestId(wireTransferRequestEntity.getRequestId()),
                new FromMembershipId(wireTransferRequestEntity.getFromMembershipId()),
                new ToMembershipId(wireTransferRequestEntity.getToMembershipId()),
                new ToBankName(wireTransferRequestEntity.getToBankName()),
                new ToBankAccountNumber(wireTransferRequestEntity.getToBankAccountNumber()),
                WireTransferType.EXTERNAL_BANK,
                new WireTransferAmount(wireTransferRequestEntity.getWireTransferAmount()),
                WireTransferRequestStatus.SUCCESS
        );
    }

    public WireTransferRequestEntity from(WireTransferRequestDomain domain) {
        return WireTransferRequestEntity.of(
                domain.getWireTransferRequestId(),
                null,
                domain.getToMembershipId(),
                domain.getToBankName(),
                domain.getToBankAccountNumber(),
                domain.getWireTransferAmount(),
                domain.getWireTransferType(),
                domain.getWireTransferRequestStatus(),
                true
        );
    }

}
