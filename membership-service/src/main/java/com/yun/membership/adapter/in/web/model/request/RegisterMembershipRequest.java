package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.RegisterMembershipCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterMembershipRequest(
        @NotNull
        @NotBlank
        @Pattern(regexp = "[a-z\\d]{10,12}",
                message = "특수문자를 제외한 12자 이내 가능합니다.")
        String membershipId,
        @NotNull
        @NotBlank
        @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,10}",
                message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 10자의 비밀번호여야 합니다.")
        String membershipPw,
        @Email
        @NotBlank
        String membershipEmail,
        @NotNull
        @NotBlank
        @Pattern(regexp = "[ㄱ-ㅎ가-힣]", message = "이름은 한글만 가능합니다.")
        String name,
        @NotNull
        @NotBlank
        String address
) {
    public RegisterMembershipCommand toCommand() {
        return RegisterMembershipCommand.of(
                this.membershipId,
                this.membershipPw,
                this.membershipEmail,
                this.name,
                this.address,
                false
        );
    }
}
