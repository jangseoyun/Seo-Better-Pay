package com.yun.moneyqueryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.yun.*","com.yun.common", "com.yun.loggingservice"})
@SpringBootApplication
public class MoneyQueryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoneyQueryServiceApplication.class, args);
    }

}
