package com.yun.settlementservice.batch.job;

import com.yun.settlementservice.batch.tasklet.SettlementTasklet;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class SettlementJobConfig {

    private final SettlementTasklet settlementTasklet;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Job settlementJob(JobRepository jobRepository) {
        return new JobBuilder("settlement", jobRepository)
                .start(settlementStep(jobRepository))
                .build();
    }

    @Bean
    public Step settlementStep(JobRepository jobRepository) {
        return new StepBuilder("settlementStep", jobRepository)
                .tasklet(settlementTasklet, transactionManager)
                .build();
    }
}
