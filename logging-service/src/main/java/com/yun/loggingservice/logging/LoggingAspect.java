package com.yun.loggingservice.logging;

import com.yun.loggingservice.kafka.LoggingProducer;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final LoggingProducer loggingProducer;
    private String topic;

    public LoggingAspect(LoggingProducer loggingProducer) {
        this.loggingProducer = loggingProducer;
    }

    @Before("execution(* com.yun.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        loggingProducer.sendMassage("logging", "Before executing method: " + methodName);
        //produce access logging
    }

}
