package com.yun.preapisecure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.yun.*")
public class PreApiSecureApplication {

	public static void main(String[] args) {
		SpringApplication.run(PreApiSecureApplication.class, args);
	}

}
