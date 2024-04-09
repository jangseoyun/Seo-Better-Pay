package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.ReadMembershipCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

//TODO: spring security
public record ReadMembershipRequest(
        @NotNull @NotBlank @NotEmpty
        @Length(min = 5, max = 12, message = "아이디는 5자 ~ 12자 사이를 입력합니다.")
        @Pattern(regexp = "[a-z\\d]{5,12}", message = "가입된 아이디 형태가 아닙니다.")
        String membershipId
) {
    public ReadMembershipCommand toCommand() {

        return ReadMembershipCommand.of(membershipId);
    }

}
