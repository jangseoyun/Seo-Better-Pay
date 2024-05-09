package com.yun.loggingservice.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DisplayName("TODO")
@SpringBootTest
class LoggingHandlerTest {

    @InjectMocks
    private LoggingHandler loggingHandler;

    @Mock
    private KafkaConsumer<String, String> loggingConsumer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loggingHandler = new LoggingHandler(loggingConsumer);
    }

    @ParameterizedTest
    @MethodSource("provideStringForConsumer")
    @DisplayName("Name")
    void Name(String consumedString) {
        //given

        //when
        //mocking
        when(loggingConsumer.poll(any(Duration.class)))
                .thenReturn(createMockRecordsFromString(consumedString));

        //then
        loggingHandler.consume();
    }

    private static Stream<String> provideStringForConsumer() {
        return Stream.of(
                new String("[log] hello"),
                new String("[logging] hello")
        );
    }

    private static ConsumerRecords<String, String> createMockRecordsFromString(String consumedString) {
        List list = new ArrayList<>();
        ConsumerRecord<String, String> dummyRecord
                = new ConsumerRecord<>("testTopic", 0, 0, "key", consumedString);
        list.add(dummyRecord);

        Map<TopicPartition, List<ConsumerRecord<String, String>>> map
                = new HashMap<>();
        map.put(new TopicPartition("testTopic", 0), list);
        return new ConsumerRecords<>(map);

    }
}