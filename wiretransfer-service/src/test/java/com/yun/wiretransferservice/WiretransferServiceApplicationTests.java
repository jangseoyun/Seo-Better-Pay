package com.yun.wiretransferservice;

import com.yun.wiretransferservice.adapter.in.web.model.WireTransferType;
import com.yun.wiretransferservice.application.port.in.WireTransferCommand;
import com.yun.wiretransferservice.application.port.out.WireTransferPort;
import com.yun.wiretransferservice.application.port.out.WireTransferRequestPort;
import com.yun.wiretransferservice.application.port.out.banking.BankingForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.membership.MembershipForWireTransferPort;
import com.yun.wiretransferservice.application.port.out.membership.MembershipStatus;
import com.yun.wiretransferservice.application.port.out.money.MoneyForWireTransferPort;
import com.yun.wiretransferservice.application.service.WireTransferService;
import com.yun.wiretransferservice.domain.WireTransfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class WiretransferServiceApplicationTests {

	//inject mocks
	//테스트 하고자 하는 로직을 포함하는 클래스 대상
	@InjectMocks
	private WireTransferService wireTransferService;

	//inject
	@Mock
	private WireTransferRequestPort wireTransferRequestPort;
	@Mock
	private WireTransferPort wireTransferPort;
	@Mock
	private MembershipForWireTransferPort membershipForWireTransferPort;
	@Mock
	private MoneyForWireTransferPort moneyForWireTransferPort;
	@Mock
	private BankingForWireTransferPort bankingForWireTransferPort;

	@BeforeEach
	public void setUp() {
		//이 라인이 injectMocks가 포함된 클래스에
		//@Mock들을 주입시켜 준다
		MockitoAnnotations.openMocks(this);

		/**
		 * @NOTE
		 * private final 필드의 경우, setter를 통해 주입할 수 없기 때문에 (서버가 올라갈 때 한번만 올리겠다)
		 * reflection or constructor를 통해 수동으로 주입해야 한다.
		 */
		wireTransferService
				= new WireTransferService(wireTransferRequestPort, wireTransferPort, membershipForWireTransferPort, moneyForWireTransferPort, bankingForWireTransferPort);
	}

	@ParameterizedTest
	@MethodSource("provideRequestWireTransferCommand")
	void requestWireTransferTestWhenFromMembershipInvalid(WireTransferCommand testCommand) {
		//System.out.println("requestWireTransfer 메서드 테스트: " + wireTransferService);

		//1. 어떤 결과가 나올지에 대해서 정의
		WireTransfer want = null;

		//2. mocking을 위한 dummy data가 있다면, 그 data는 먼저 만들어 줄것

		//3. 그 결과를 위해 mocking을 해준다
		when(wireTransferRequestPort.createWireTransferHistory(testCommand.toDomain()))
				.thenReturn(null);

		doReturn(new MembershipStatus(testCommand.getFromMembershipId(), false))
				.when(membershipForWireTransferPort).getMembershipStatus(testCommand.getFromMembershipId());

		//4. 그리고 그 mocking 된 mock 들을 사용해서, 테스트를 진행할 것이다.
		WireTransfer got = wireTransferService.requestWireTransfer(testCommand);

		//5. verify를 통해서, 테스트가 잘 진행되었는지 확인
		// 해당 port를 한번만 호출하는 것
		//verify(wireTransferRequestPort, times(1)).createWireTransferHistory(testCommand.toDomain());
		//verify(membershipForWireTransferPort, times(1)).getMembershipStatus(testCommand.getFromMembershipId());

		//6. assert를 통해, 최종적으로 이 테스트가 잘 진행되었는지 확인
		assertEquals(want, got);
	}

	private static Stream<WireTransferCommand> provideRequestWireTransferCommand() {
		return Stream.of(
				WireTransferCommand.of(
						"5",
						"1",
						"bankname",
						"1234-234-23412",
						5000,
						WireTransferType.SEOBETTERPAY_MEMBER.toString(),
						true
				)
		);
	}

}
