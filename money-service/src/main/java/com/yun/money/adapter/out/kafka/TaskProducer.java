package com.yun.money.adapter.out.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yun.common.RechargingMoneyTask;
import com.yun.money.application.port.out.SendRechargingMoneyTaskPort;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class TaskProducer implements SendRechargingMoneyTaskPort {

    private final KafkaProducer<String, String> producer;
    private final ObjectMapper objectMapper;
    @Value("${kafka.clusters.bootstrapservers}")
    private String bootstrapServers;
    @Value("${task.topic}")
    private String topic;

    public TaskProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        this.producer = new KafkaProducer<>(properties);
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void sendRechargingMoneyTaskPort(RechargingMoneyTask moneyTask) {
        String jsonStringToProduce;
        try {
            jsonStringToProduce = objectMapper.writeValueAsString(moneyTask);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, moneyTask.getTaskId(), jsonStringToProduce);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                //메세지 로그 출력
            } else {
                exception.printStackTrace();
            }
        });
    }
}
