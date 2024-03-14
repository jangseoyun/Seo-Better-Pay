package com.yun.common;

import lombok.*;

import java.util.List;

/**
 * pay money 증액 (충전)을 위한 task
 * toBankName: 법인 계좌
 * toBankAccountNumber: 법인 계좌 번호
 * moneyAmount: 원화
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RechargingMoneyTask {
    private String taskId;
    private String taskName;
    private String membershipId;
    private List<SubTask> subTaskList;
    private String toBankName;
    private String toBankAccountNumber;
    private Integer moneyAmount;

    @Builder
    public RechargingMoneyTask(String taskId,
                               String taskName,
                               String membershipId,
                               List<SubTask> subTaskList,
                               String toBankName,
                               String toBankAccountNumber,
                               Integer moneyAmount) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.membershipId = membershipId;
        this.subTaskList = subTaskList;
        this.toBankName = toBankName;
        this.toBankAccountNumber = toBankAccountNumber;
        this.moneyAmount = moneyAmount;
    }
}
