package com.yun.openbanking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.yun.*","com.yun.common", "com.yun.loggingservice"})
@SpringBootApplication
public class OpenbankingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenbankingServiceApplication.class, args);
	}

}
