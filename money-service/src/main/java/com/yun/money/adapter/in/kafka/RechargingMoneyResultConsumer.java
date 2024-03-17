package com.yun.money.adapter.in.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.CountDownLatchManager;
import com.yun.common.RechargingMoneyTask;
import com.yun.common.SubTask;
import com.yun.loggingservice.kafka.LoggingProducer;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Slf4j
@Component
public class RechargingMoneyResultConsumer {

    private final KafkaConsumer<String, String> consumer;
    private final LoggingProducer loggingProducer;
    private final ObjectMapper objectMapper;
    @NotNull
    private final CountDownLatchManager countDownLatchManager;

    public RechargingMoneyResultConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                         @Value("${task.result.topic}") String topicName,
                                         CountDownLatchManager countDownLatchManager,
                                         LoggingProducer loggingProducer) {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put("group.id", "pay-group");//consumer group
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        this.loggingProducer = loggingProducer;
        this.countDownLatchManager = countDownLatchManager;
        this.objectMapper = new ObjectMapper();
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topicName));

        Thread consumerThread = new Thread(() -> {

            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        log.info("received message: key = {}, value = {}", record.key(), record.value());

                        //record: RechargingMoneyTask all subtask is done
                        /*try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }*/

                        RechargingMoneyTask task;
                        try {
                            task = objectMapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                        List<SubTask> subTaskList = task.getSubTaskList();

                        boolean taskResult = true;
                        //validation membership
                        //validation banking
                        for (SubTask subTask : subTaskList) {
                            if (subTask.getStatus().equals("fail")) {
                                taskResult = false;//한번이라도 false라면 해당 subtask는 실패한것
                                break;
                            }
                        }

                        //모두 정상적으로 성공 했다면
                        if (taskResult) {
                            this.loggingProducer.sendMassage(task.getTaskId(), "task success");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "success");
                        } else {
                            this.loggingProducer.sendMassage(task.getTaskId(), "task failed");
                            this.countDownLatchManager.setDataForKey(task.getTaskId(), "failed");
                        }
                        //await가 풀린다
                        this.countDownLatchManager.getCountDownLatch(task.getTaskId()).countDown();
                    }
                }
            } finally {
                consumer.close();
            }
        });

        consumerThread.start();
    }
}
