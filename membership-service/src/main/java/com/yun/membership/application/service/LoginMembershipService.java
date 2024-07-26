package com.yun.membership.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.membership.adapter.in.web.model.MembershipResult;
import com.yun.membership.application.port.in.LoginMembershipCommand;
import com.yun.membership.application.port.in.LoginMembershipUseCase;
import com.yun.membership.application.port.in.MembershipRefreshTokenCommand;
import com.yun.membership.application.port.in.TokenValidationCommand;
import com.yun.membership.application.port.out.ModifyMembershipPort;
import com.yun.membership.application.port.out.ReadMembershipPort;
import com.yun.membership.domain.LoginInfoValidator;
import com.yun.membership.domain.Membership;
import com.yun.membership.exception.MembershipModuleException;
import com.yun.membership.jwt.JwtCreateFactory;
import com.yun.membership.jwt.JwtToken;
import com.yun.membership.jwt.LoginAuthTokenPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import static com.yun.membership.exception.MembershipErrorCode.INVALID_USER_ID_PASSWORD;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LoginMembershipService implements LoginMembershipUseCase {

    private final LoginAuthTokenPort loginAuthTokenPort;
    private final ReadMembershipPort readMembershipPort;
    private final LoginInfoValidator loginInfoValidator;
    private final ModifyMembershipPort modifyMembershipPort;

    @Override
    @Transactional
    public MembershipResult requestLogin(LoginMembershipCommand command) {

        Membership getResultmembership = readMembershipPort.findByMembershipId(command.getMembershipId());
        log.info("login getMembership: {}", getResultmembership);

        boolean checkedMembershipPw = loginInfoValidator.checkMembershipPw(
                getResultmembership.getMembershipPw(), command.getMembershipPw()
        );

        if (!checkedMembershipPw) {
            throw new MembershipModuleException(INVALID_USER_ID_PASSWORD, INVALID_USER_ID_PASSWORD.getMessage());
        }

        JwtToken jwtToken = createJwtToken(getResultmembership.getMembershipId());
        log.info("login jwtToken: {}", jwtToken);
        modifyMembershipPort.insertRefreshToken(jwtToken);

        return MembershipResult.success(jwtToken);
    }

    @Override
    public MembershipResult requestRefreshToken(MembershipRefreshTokenCommand command) {
        String refreshToken = command.getRefreshToken();
        boolean validatedJwtToken = loginAuthTokenPort.validateJwtToken(refreshToken);

        if (validatedJwtToken) {
            JwtToken.MembershipId membershipId = loginAuthTokenPort.checkMembershipIdByToken(refreshToken);

            Membership membership = readMembershipPort.findByMembershipId(membershipId.getMembershipId());

            if (!membership.getRefreshToken().equals(command.getRefreshToken())) {
                log.info("requestRefreshToken: {} 일치하지 않음", command.getRefreshToken());
                return null;
            }

            if (membership.isValid()) {
                String newRefreshToken = loginAuthTokenPort.createJwtToken(membershipId);
                JwtToken jwtToken = JwtCreateFactory.newJwtToken(membershipId, newRefreshToken, refreshToken);
                log.info("createRefreshToken: {}", jwtToken);
                return MembershipResult.success(jwtToken);
            }

        }

        return null;
    }

    @Override
    public MembershipResult requestTokenValidation(TokenValidationCommand command) {
        boolean validatedJwtToken = loginAuthTokenPort.validateJwtToken(command.getJwtToken());

        if (!validatedJwtToken) {
            return MembershipResult.error(command.getJwtToken());
        }

        return MembershipResult.success(command.getJwtToken());
    }

    private JwtToken createJwtToken(String membershipId) {
        JwtToken.MembershipId accessMembershipId = JwtCreateFactory.newMembershipId(membershipId);
        String jwtToken = loginAuthTokenPort.createJwtToken(accessMembershipId);
        String refreshToken = loginAuthTokenPort.createRefreshToken(accessMembershipId);
        return JwtCreateFactory.newJwtToken(accessMembershipId, jwtToken, refreshToken);
    }

}
