package org.bitbucket.transaction.listener;

import org.bitbucket.transaction.entity.TransactionFeedback;
import org.bitbucket.transaction.event.NotificationEvent;
import org.bitbucket.transaction.repository.TransactionFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

    private static final String topic = "notification";

    @Autowired
    private TransactionFeedbackRepository transactionFeedbackRepository;

    @KafkaListener(topics = topic, group = "1")
    public void handle(NotificationEvent event) {
        System.out.println("Received Notification: " + event.getCustomerId());

        TransactionFeedback transactionFeedback = new TransactionFeedback();
        transactionFeedback.setCustomerId(event.getCustomerId());
        transactionFeedback.setProductId(event.getProductId());

        transactionFeedbackRepository.save(transactionFeedback);
    }
}
