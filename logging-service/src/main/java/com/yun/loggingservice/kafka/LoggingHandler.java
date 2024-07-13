package com.yun.loggingservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class LoggingHandler {

    private final KafkaConsumer<String, String> loggingConsumer;

    public LoggingHandler(KafkaConsumer<String, String> loggingConsumer) {
        this.loggingConsumer = loggingConsumer;
        this.consume();
    }

    public void consume() {
        Thread consumerThread = new Thread(() -> {

            try {
                while (true) {
                    ConsumerRecords<String, String> records = loggingConsumer.poll(Duration.ofSeconds(1));
                    if (records.isEmpty()) {
                        continue;
                    }

                    for (ConsumerRecord<String, String> record : records) {
                        handle(record);
                    }
                }
            } finally {
                loggingConsumer.close();
            }

        });

        consumerThread.start();
    }

    private void handle(ConsumerRecord<String, String> record) {
        if (record.value().startsWith("[logging]")) {
            return;
        }

        System.out.println("received message: " + record.value());
    }
}
