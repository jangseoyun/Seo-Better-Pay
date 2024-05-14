package com.yun.money.adapter.in.web.model;

import java.util.List;

public record MembershipListRequest(
        List<String> membershipIds
) {
}
