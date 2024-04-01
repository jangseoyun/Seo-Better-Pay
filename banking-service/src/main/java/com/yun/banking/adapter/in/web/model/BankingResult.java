package com.yun.banking.adapter.in.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankingResult<T> {
    private String status;
    private T result;

    public static <T> BankingResult<T> error(T result) {
        return new BankingResult("ERROR", result);
    }

    public static <T> BankingResult<T> success(T result) {
        return new BankingResult("SUCCESS", result);
    }
}
