package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.LoginMembershipCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * membershipId를 통해 로그인이 가능하다
 */
public record LoginMembershipRequest (
        @NotNull @NotBlank @NotEmpty
        @Length(min = 5, max = 12, message = "아이디는 5자 ~ 12자 사이를 입력합니다.")
        @Pattern(regexp = "[a-z\\d]{5,12}", message = "가입된 아이디 형태가 아닙니다.")
        String membershipId,
        @NotNull @NotBlank @NotEmpty
        String membershipPw
) {
    public LoginMembershipCommand toCommand() {

        return LoginMembershipCommand.of(membershipId, membershipPw);
    }

}
