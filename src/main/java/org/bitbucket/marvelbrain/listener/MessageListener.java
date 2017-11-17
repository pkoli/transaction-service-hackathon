package org.bitbucket.marvelbrain.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @KafkaListener(topics = "test", group = "1")
    public void handle(String message) {
        System.out.println("Received Message in group: " + message);
    }
}
