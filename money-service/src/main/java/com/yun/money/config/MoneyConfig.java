package com.yun.money.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.yun.common", "com.yun.loggingservice"})
public class MoneyConfig {
}
