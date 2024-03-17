package com.yun.membership.adapter.in.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResult<T> {
    private String status;
    private T result;

    public static <T> MembershipResult<T> error(T result) {
        return new MembershipResult("ERROR", result);
    }

    public static <T> MembershipResult<T> success(T result) {
        return new MembershipResult("SUCCESS", result);
    }
}
