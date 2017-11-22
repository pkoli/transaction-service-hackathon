package org.bitbucket.transaction.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bitbucket.transaction.entity.TransactionFeedback;
import org.bitbucket.transaction.event.NotificationEvent;
import org.bitbucket.transaction.repository.TransactionFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class NotificationEventListener {

    private static final String topic = "notification";

    @Autowired
    private TransactionFeedbackRepository transactionFeedbackRepository;

    @KafkaListener(topics = topic, group = "1")
    public void handle(String notificationEvent) {
        ObjectMapper mapper = new ObjectMapper();

        NotificationEvent event = null;
        try {
            event = mapper.readValue(notificationEvent, NotificationEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Received Notification: " + event.getCustomerId());

        TransactionFeedback transactionFeedback = new TransactionFeedback();
        transactionFeedback.setCustomerId(event.getCustomerId());
        transactionFeedback.setProductId(String.valueOf(event.getProductId()));

        transactionFeedbackRepository.save(transactionFeedback);
    }
}
