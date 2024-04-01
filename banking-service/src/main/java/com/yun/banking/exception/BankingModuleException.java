package com.yun.banking.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BankingModuleException extends RuntimeException{
    private BankingServiceErrorCode bankingServiceErrorCode;
    private String message;
}
