package com.yun.banking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.yun.common"})
@SpringBootApplication
public class BankingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingServiceApplication.class, args);
	}

}
