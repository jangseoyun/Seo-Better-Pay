package com.yun.money.adapter.out.persistence;

import com.yun.common.PersistenceAdapter;
import com.yun.money.adapter.out.persistence.factory.PayWalletMoneyFactory;
import com.yun.money.application.port.out.DecreaseMoneyAmountPort;
import com.yun.money.application.port.out.IncreaseMoneyAmountPort;
import com.yun.money.domain.PayWalletMoney;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AdjustMoneyAmountAdapter implements IncreaseMoneyAmountPort, DecreaseMoneyAmountPort {

    private final PayWalletMoneyJpaRepository payWalletMoneyJpaRepository;
    private final PayWalletMoneyMapper mapper;

    @Override
    public PayWalletMoney decreaseMoneyAmount(PayWalletMoney payWalletMoney) {
        PayWalletMoneyEntity payWalletMoneyEntity = payWalletMoneyJpaRepository.save(PayWalletMoneyFactory.toEntity(payWalletMoney));
        return mapper.mapToDomainWalletMoney(payWalletMoneyEntity);
    }

    @Override
    public Integer moneyTotalAmount(String bankAccountNumber) {
        return payWalletMoneyJpaRepository
                .moneyTotalAmount(bankAccountNumber);
    }

    @Override
    public PayWalletMoney increaseMoneyAmount(PayWalletMoney payWalletMoney) {
        PayWalletMoneyEntity payWalletMoneyEntity
                = payWalletMoneyJpaRepository.save(PayWalletMoneyFactory.toEntity(payWalletMoney));
        return mapper.mapToDomainWalletMoney(payWalletMoneyEntity);
    }
}
