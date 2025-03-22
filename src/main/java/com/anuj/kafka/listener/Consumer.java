package com.anuj.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @KafkaListener(topics = "topic1", groupId = "test")
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
