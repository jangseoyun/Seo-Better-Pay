package com.yun.membership.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.membership.adapter.in.web.model.request.RegisterMembershipRequest;
import com.yun.membership.application.port.in.RegisterMembershipCommand;
import com.yun.membership.application.port.in.RegisterMembershipUseCase;
import com.yun.membership.domain.Membership;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("멤버십 controller - adapter.in.web")
@AutoConfigureMockMvc
@WebMvcTest(RegisterMembershipController.class)
class RegisterMembershipControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @MockBean
    private RegisterMembershipUseCase registerMembershipUseCase;

    public RegisterMembershipControllerTest(@Autowired MockMvc mockMvc,
                                            @Autowired ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @DisplayName("[멤버십 신규 가입] - 성공시 membership 정보를 반환한다")
    @Test
    void givenRegisterRequestInfo_whenSavingMembership_thenReturnMembership() throws Exception {
        //given
        RegisterMembershipRequest registerMembershipRequest = createdRegisterMembershipRequest();
        given(registerMembershipUseCase.registerMembership(any(RegisterMembershipCommand.class))).willReturn(any(Membership.class));

        //when
        mockMvc.perform(post("/membership/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerMembershipRequest))
        ).andExpect(status().isOk());

        //then
        then(registerMembershipUseCase).should().registerMembership(any(RegisterMembershipCommand.class));
    }

    @DisplayName("[멤버십 회원 조회] - 실패")
    @Test
    void givenMembershipId_whenFindingMembership_thenReturnMembershipInfo() {
        //TODO: exception 구현 필요
    }

    private RegisterMembershipRequest createdRegisterMembershipRequest() {
        return new RegisterMembershipRequest(
                "seoseo",
                "addresstesttest",
                "seoseo@email.com",
                "name",
                ""
        );
    }
}