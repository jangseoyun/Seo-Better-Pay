package com.yun.loggingservice.kafka;

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
public class LoggingConsumer {

    private KafkaConsumer<String, String> consumer;
    @Value("${kafka.clusters.bootstrapservers}")
    private String bootstrapServers;
    @Value("${logging.topic}")
    private String topicName;

    public LoggingConsumer() {

        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put("group.id", "pay-group");//consumer group
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singletonList(topicName));

        Thread consumerThread = new Thread(() -> {

            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.println("received message: " + record.value());
                    }
                }
            } finally {
                consumer.close();
            }

        });

        consumerThread.start();
    }
}
