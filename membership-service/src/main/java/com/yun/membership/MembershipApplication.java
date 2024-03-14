package com.yun.membership;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.yun.common", "com.yun.loggingservice"})
@SpringBootApplication
public class MembershipApplication {
	public static void main(String[] args) {
		SpringApplication.run(MembershipApplication.class, args);
	}
}
