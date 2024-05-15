package com.yun.money.application.service;

import com.yun.common.anotation.UseCase;
import com.yun.money.application.port.in.MoneyAggregationUseCase;
import com.yun.money.application.port.out.GetMembershipForMoneyPort;
import com.yun.money.application.port.out.ReadMoneyAmountPort;
import com.yun.money.domain.MemberMoneyWallet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@UseCase
@RequiredArgsConstructor
@Transactional
public class MoneyAggregationService implements MoneyAggregationUseCase {

    private final GetMembershipForMoneyPort getMembershipForMoneyPort;
    private final ReadMoneyAmountPort readMoneyAmountPort;

    @Transactional(readOnly = true)
    @Override
    public int getMoneySumByAddress(String addressKeyword) {
        //비즈니스 모델
        //1. member중 addressKeyword에 거주중인 회원 조회
        List<String> membershipIdsResultByAddress = getMembershipForMoneyPort.getMembershipByAddress(addressKeyword);
        log.info("MoneyAggregationService getMoneySumByAddress: {}", membershipIdsResultByAddress);

        List<List<String>> membershipPartitionList = null;
        if (membershipIdsResultByAddress.size() > 100) {
            //100개씩 나눈 리스트를 담은 list
            membershipPartitionList = partitionList(membershipIdsResultByAddress, 100);
        }
        
        //100개씩 묶음 단위로 요청
        int sum = 0;
        for (List<String> partitionedList : membershipPartitionList) {
            List<MemberMoneyWallet> membershipRegionTotalMoney = readMoneyAmountPort.findMemberMoneyListByMembershipIds(membershipIdsResultByAddress);
            log.info("membershipRegionTotalMoney list: {}", membershipRegionTotalMoney);

            for (MemberMoneyWallet memberMoneyWallet : membershipRegionTotalMoney) {
                sum += memberMoneyWallet.getMoneyTotalAmount();
            }
        }

        return sum;
    }

    //list를 n개씩 묶어서 List<List<T>>로 만드는 메서드
    private <T> List<List<T>> partitionList(List<T> membershipIdsResultByAddress, int partitionSize) {
        return IntStream.range(0, membershipIdsResultByAddress.size())
                .boxed()
                .collect(Collectors.groupingBy(index -> index / partitionSize))
                .values()
                .stream()
                .map(indices -> indices.stream().map(membershipIdsResultByAddress::get).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
