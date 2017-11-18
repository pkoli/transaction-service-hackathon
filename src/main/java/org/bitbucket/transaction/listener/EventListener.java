package org.bitbucket.transaction.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private static final String topic = "notification";

    @KafkaListener(topics = topic, group = "1")
    public void handle(String event) {
        System.out.println("Received Notification: " + event);
    }
}
