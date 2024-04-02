package com.yun.banking.application.port.in;

import com.yun.banking.adapter.in.web.model.request.TransferFirmBankingRequest;
import com.yun.banking.adapter.out.external.bank.model.CallExternalTransferRequest;
import com.yun.banking.domain.TransferFirmBanking;
import com.yun.banking.domain.TransferRequestStatusEnum;
import com.yun.common.SelfValidating;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static com.yun.banking.domain.TransferFirmBanking.*;

@Getter
@EqualsAndHashCode
public class TransferFirmBankingCommand extends SelfValidating<TransferFirmBankingRequest> {
    @NotNull
    @NotEmpty
    private String fromBankName;
    @NotNull
    @NotEmpty
    private String fromBankAccountNumber;
    @NotNull
    @NotEmpty
    private String toBankName;
    @NotNull
    @NotEmpty
    private String toBankAccountNumber;
    @NotNull
    @NotEmpty
    private int transferAmount;

    private TransferFirmBankingCommand(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int transferAmount) {
        this.fromBankName = fromBankName;
        this.fromBankAccountNumber = fromBankAccountNumber;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.transferAmount = transferAmount;
    }

    public static TransferFirmBankingCommand of(String fromBankName, String fromBankAccountNumber, String toBankName, String toBankAccountNumber, int transferAmount) {
        return new TransferFirmBankingCommand(fromBankName, fromBankAccountNumber, toBankName, toBankAccountNumber, transferAmount);
    }

    public TransferFirmBanking toRequestTransferFirmBanking() {
        return generateTransferFirmBaking(
                new TransferFirmBankingId(null),
                new FromBankName(fromBankName),
                new FromBankAccountNumber(fromBankAccountNumber),
                new ToBankName(toBankName),
                new ToBankAccountNumber(toBankAccountNumber),
                new TransferAmount(transferAmount),
                new TransferRequestStatus(TransferRequestStatusEnum.REQUEST)
        );
    }

    public CallExternalTransferRequest toExternalRequest() {
        return new CallExternalTransferRequest(
                fromBankName,
                fromBankAccountNumber,
                toBankName,
                toBankAccountNumber,
                ""
        );
    }
}
