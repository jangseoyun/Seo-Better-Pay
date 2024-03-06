package com.yun.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.yun.*"})
public class MembershipApplication {
	public static void main(String[] args) {
		SpringApplication.run(MembershipApplication.class, args);
	}
}
