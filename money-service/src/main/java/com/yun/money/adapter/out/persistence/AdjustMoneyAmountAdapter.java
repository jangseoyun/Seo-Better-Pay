package com.yun.money.adapter.out.persistence;

import com.yun.common.PersistenceAdapter;
import com.yun.money.adapter.out.persistence.factory.PayWalletMoneyFactory;
import com.yun.money.application.port.out.DecreaseMoneyAmountPort;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.application.port.out.ReadMoneyAmountPort;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class AdjustMoneyAmountAdapter implements IncreaseMoneyAmountPort, DecreaseMoneyAmountPort, ReadMoneyAmountPort {

    private final PayWalletMoneyJpaRepository payWalletMoneyJpaRepository;
    private final PayWalletMoneyMapper mapper;

    @Override
    public PayWalletMoney decreaseMoneyAmount(PayWalletMoney payWalletMoney) {
        PayWalletMoneyEntity payWalletMoneyEntity = payWalletMoneyJpaRepository.save(PayWalletMoneyFactory.toEntity(payWalletMoney));
        return mapper.mapToDomainWalletMoney(payWalletMoneyEntity);
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
                .map(money -> mapper.mapToDomainWalletMoney(money))
                .collect(Collectors.toList());
    }

    @Override
    public List<PayWalletMoney> getPayMoneyHistory(String membershipId) {
        return payWalletMoneyJpaRepository.getDecreaseMoneyHistory(membershipId)
                .stream()
                .map(money -> mapper.mapToDomainWalletMoney(money))
                .collect(Collectors.toList());
    }

    @Override
    public PayWalletMoney increaseMoneyAmount(PayWalletMoney payWalletMoney) {
        PayWalletMoneyEntity payWalletMoneyEntity
                = payWalletMoneyJpaRepository.save(PayWalletMoneyFactory.toEntity(payWalletMoney));
        return mapper.mapToDomainWalletMoney(payWalletMoneyEntity);
    }
}
