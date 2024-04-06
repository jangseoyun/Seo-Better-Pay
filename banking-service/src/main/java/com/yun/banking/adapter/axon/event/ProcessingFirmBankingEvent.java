package com.yun.banking.adapter.axon.event;

import com.yun.banking.domain.TransferRequestStatusEnum;
import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProcessingFirmBankingEvent {
    private TransferRequestStatusEnum transferRequestStatus;
}
