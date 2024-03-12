package com.yun.loggingservice.kafka;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class LoggingProducer {
    private KafkaProducer<String, String> producer;
    @Value("${kafka.clusters.bootstrapservers}")
    private String bootstrapServers;
    @Value("${logging.topic}")
    private String topic;

    public LoggingProducer() {
        Properties properties = new Properties();
        //kafka:29092
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        this.producer = new KafkaProducer<String, String>(properties);
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
