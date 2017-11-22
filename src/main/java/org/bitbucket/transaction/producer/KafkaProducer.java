package org.bitbucket.transaction.producer;

import org.bitbucket.transaction.event.AnalyseTransactionEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer implements Producer{

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value(value = "${kafka.topic}")
    private String topic;

    @Override
    public void sendEvent(AnalyseTransactionEvent event) {
        kafkaTemplate.send(topic, event);
    }
}
