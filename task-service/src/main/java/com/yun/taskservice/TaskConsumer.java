package com.yun.taskservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.RechargingMoneyTask;
import com.yun.common.SubTask;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
public class TaskConsumer {
    private final KafkaConsumer<String, String> consumer;
    private final TaskResultProducer taskResultProducer;
    private final ObjectMapper objectMapper;

    public TaskConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                        @Value("${task.topic}") String topicName, TaskResultProducer taskResultProducer, ObjectMapper objectMapper) {


        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put("group.id", "pay-group");//consumer group
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        this.taskResultProducer = taskResultProducer;
        this.objectMapper = new ObjectMapper();
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topicName));

        Thread consumerThread = new Thread(() -> {

            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        RechargingMoneyTask task;

                        //record: RechargingMoneyTask (jsonString)
                        //task run
                        try {
                            task = objectMapper.readValue(record.value(), RechargingMoneyTask.class);
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }

                        //task result 판단
                        for (SubTask subTask : task.getSubTaskList()) {
                            //어떤 subtask 인지 판단해야함 (membership, banking)
                            //external port, adapter
                            //hexagonal architecture
                            //모든 sub task 가 정상적으로 종료 되었다
                            subTask.setStatus("success");
                        }

                        //판단된 result를 가지고 produce task result
                        //producer task result
                        this.taskResultProducer.sendTaskResult(task.getTaskId(), task);
                    }
                }
            } finally {
                consumer.close();
            }

        });

        consumerThread.start();
    }
}
