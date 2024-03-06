package com.yun.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.yun.membership", "com.yun.common"})
public class MembershipApplication {
	public static void main(String[] args) {
		SpringApplication.run(MembershipApplication.class, args);
	}
}
