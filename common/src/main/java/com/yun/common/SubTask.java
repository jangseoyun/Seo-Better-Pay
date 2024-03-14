package com.yun.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * moneyTaskType: BANKING, MEMBERSHIP
 * status: success, fail, ready
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubTask {
    private String membershipId;
    private String subTaskName;
    private MoneyTaskType moneyTaskType;
    private String status;
}
