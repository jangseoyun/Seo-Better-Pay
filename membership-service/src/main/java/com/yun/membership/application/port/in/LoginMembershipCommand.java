package com.yun.membership.application.port.in;

import com.yun.common.SelfValidating;
import com.yun.membership.adapter.in.web.model.request.LoginMembershipRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

/**
 * membershipId
 * - 문자열 길이 max 12
 * - null, empty, blank 체크
 * membershipPw
 * - null, empty, blank 체크
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(callSuper = false)
public class LoginMembershipCommand extends SelfValidating<LoginMembershipRequest> {

    private String membershipId;
    private String membershipPw;

    public LoginMembershipCommand(String membershipId, String membershipPw) {
        this.membershipId = membershipId;
        this.membershipPw = membershipPw;
        this.validateSelf();
    }

    public static LoginMembershipCommand of(String membershipId, String membershipPw) {
        return new LoginMembershipCommand(membershipId, membershipPw);
    }
}
