package com.yun.money.adapter.out.persistence;

import com.yun.common.anotation.PersistenceAdapter;
import com.yun.money.adapter.out.persistence.factory.PayWalletMoneyFactory;
import com.yun.money.application.port.in.RechargeMoneyAmountCommand;
import com.yun.money.application.port.out.DecreaseMoneyAmountPort;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.application.port.out.ReadMoneyAmountPort;
import com.yun.money.domain.MemberMoneyWallet;
import com.yun.money.domain.PayWalletMoney;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@PersistenceAdapter
@RequiredArgsConstructor
public class AdjustMoneyAmountAdapter implements IncreaseMoneyAmountPort, DecreaseMoneyAmountPort, ReadMoneyAmountPort {

    private final PayWalletMoneyJpaRepository payWalletMoneyJpaRepository;
    private final MemberMoneyWalletJpaRepository memberMoneyWalletJpaRepository;
    private final PayWalletMoneyMapper payWalletMoneyMapper;
    private final MemberMoneyWalletMapper memberMoneyWalletMapper;

    @Override
    public PayWalletMoney decreaseMoneyAmount(PayWalletMoney payWalletMoney) {
        PayWalletMoneyEntity payWalletMoneyEntity = payWalletMoneyJpaRepository.save(PayWalletMoneyFactory.toEntity(payWalletMoney));
        return payWalletMoneyMapper.mapToDomainWalletMoney(payWalletMoneyEntity);
    }

    @Override
    public Integer moneyTotalAmount(String membershipId) {
        return payWalletMoneyJpaRepository
                .moneyTotalAmount(membershipId);
    }

    @Override
    public List<PayWalletMoney> getAddMoneyHistory(String membershipId) {
        return payWalletMoneyJpaRepository.getIncreaseMoneyHistory(membershipId)
                .stream()
                .map(money -> payWalletMoneyMapper.mapToDomainWalletMoney(money))
                .collect(Collectors.toList());
    }

    @Override
    public List<PayWalletMoney> getPayMoneyHistory(String membershipId) {
        return payWalletMoneyJpaRepository.getDecreaseMoneyHistory(membershipId)
                .stream()
                .map(money -> payWalletMoneyMapper.mapToDomainWalletMoney(money))
                .collect(Collectors.toList());
    }

    @Override
    public MemberMoneyWallet getMemberMoneyWallet(String membershipId) {
        return memberMoneyWalletJpaRepository.findById(membershipId)
                .map(wallet -> memberMoneyWalletMapper.mapToMemberMoneyWallet(wallet))
                .orElseThrow(() -> {
                    throw new EntityNotFoundException("회원 페이 wallet이 존재하지 않습니다.");
                });
    }

    @Override
    public void addMoneyToSeobetterpayByEvent(RechargeMoneyAmountCommand command) {

    }

    @Override
    public PayWalletMoney increaseMoneyAmount(PayWalletMoney payWalletMoney) {
        log.info("increaseMoneyAmount payWalletMoney: {}", payWalletMoney);

        PayWalletMoneyEntity payWalletMoneyEntity
                = payWalletMoneyJpaRepository.save(PayWalletMoneyFactory.toEntity(payWalletMoney));
        return payWalletMoneyMapper.mapToDomainWalletMoney(payWalletMoneyEntity);
    }
}
