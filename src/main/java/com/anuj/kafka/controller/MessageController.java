package com.anuj.kafka.controller;

import com.anuj.kafka.model.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
@RequiredArgsConstructor
public class MessageController {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("send")
    public ResponseEntity<Boolean> sendMessage(
            @RequestBody MessageDto messageDto
    ) {
        kafkaTemplate.send("topic1", messageDto.getMessage())
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Sent message=[" + messageDto.getMessage() +
                                "] with offset=[" + result.getRecordMetadata().offset() + "]");
                    } else {
                        System.out.println("Unable to send message=[" +
                                messageDto.getMessage() + "] due to : " + ex.getMessage());
                    }
                });
        return ResponseEntity.ok(true);
    }
}
