package com.yun.membership.adapter.in.web.model.request;

import com.yun.membership.application.port.in.ModifyMembershipCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

/**
 * 회원 정보 수정
 * email, password 수정의 경우 본인 인증 및 검증이 필요하므로 API 분리
 * request에서 받는 password는 수정을 위한 본인 확인을 위해 사용.
 */
public record ModifyMembershipRequest(
        @Length(min = 5, max = 12, message = "아이디는 5자 ~ 12자 사이를 입력합니다.")
        @Pattern(regexp = "[a-z\\d]{5,12}", message = "특수문자를 제외한 12자 이내 가능합니다.")
        String editMembershipId,
        @NotNull @NotBlank @NotEmpty
        String membershipPw,
        String address
) {
    public ModifyMembershipCommand toCommand() {
        return ModifyMembershipCommand.of(
                editMembershipId,
                membershipPw,
                address
        );
    }
}
