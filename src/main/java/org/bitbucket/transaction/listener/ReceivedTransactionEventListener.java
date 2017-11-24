package org.bitbucket.transaction.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bitbucket.transaction.event.ReceivedTransactionEvent;
import org.bitbucket.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceivedTransactionEventListener {

    private static final String topic = "customer_transaction";

    @Autowired
    private TransactionService transactionService;

    @KafkaListener(topics = topic, group = "1")
    public void handle(String notificationEvent) {
        ObjectMapper mapper = new ObjectMapper();

        ReceivedTransactionEvent event = null;
        try {
            event = mapper.readValue(notificationEvent, ReceivedTransactionEvent.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("ReceivedEvent: "+notificationEvent);

        transactionService.processTransaction(event);
    }
}
