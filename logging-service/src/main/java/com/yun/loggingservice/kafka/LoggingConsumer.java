package com.yun.loggingservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Properties;

@Component
public class LoggingConsumer {

    private KafkaConsumer<String, String> consumer;

    @Bean
    public KafkaConsumer<String, String> initializeConsumer(@Value("${kafka.clusters.bootstrapservers}") String bootstrapServers,
                                                            @Value("${logging.topic}") String topicName) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put("group.id", "pay-group");//consumer group
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer consumer = new KafkaConsumer(properties);
        consumer.subscribe(Collections.singletonList(topicName));

        return consumer;
    }
}
