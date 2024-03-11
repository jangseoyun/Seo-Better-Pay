package com.yun.common.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class LoggingProducer {
    private final KafkaProducer<String, String> producer;
    private final String topic;

    public LoggingProducer(@Value("${kafka.clusters.bootstrap.servers}") KafkaProducer<String, String> bootstrapServers,
                           @Value("${logging.topic}") String topic)
    {
        Properties properties = new Properties();
        //kafka:29092
        properties.put("bootstrap.servers", bootstrapServers);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    //kafka cluster
    public void sendMassage(String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, (metadata, exception) -> {
            if (exception == null) {
                //메세지 로그 출력
            } else {
                exception.printStackTrace();
            }
        });

    }
}
