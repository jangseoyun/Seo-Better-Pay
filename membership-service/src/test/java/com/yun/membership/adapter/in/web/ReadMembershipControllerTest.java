package com.yun.membership.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.membership.adapter.in.web.model.request.ReadMembershipRequest;
import com.yun.membership.application.port.in.ReadMembershipCommand;
import com.yun.membership.application.port.in.ReadMembershipUseCase;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("멤버십 read controller - adapter.in.web")
@AutoConfigureMockMvc
@WebMvcTest(ReadMembershipController.class)
class ReadMembershipControllerTest {
    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    @MockBean
    private ReadMembershipUseCase readMembershipUseCase;

    public ReadMembershipControllerTest(@Autowired MockMvc mockMvc,
                                        @Autowired ObjectMapper objectMapper) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
    }

    @DisplayName("[멈버십 조회] - 조회 성공시 해당 membership 반환")
    @Test
    void givenMembershipId_whenFindingMembership_thenReturnMembershipInfo() throws Exception {
        //given
        ReadMembershipRequest readMembershipRequest = createdReadMembershipRequest();
        given(readMembershipUseCase.getMembershipsByMemberId(any(ReadMembershipCommand.class)))
                .willReturn(any(Membership.class));

        //when
        mockMvc.perform(get("/membership/" + readMembershipRequest.membershipId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        //then
        then(readMembershipUseCase).should().getMembershipsByMemberId(any(ReadMembershipCommand.class));
    }

    private ReadMembershipRequest createdReadMembershipRequest() {
        return new ReadMembershipRequest("1");
    }
}