package com.yun.wiretransferservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.yun.common")
@SpringBootApplication
public class WiretransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WiretransferServiceApplication.class, args);
	}

}
