package org.bitbucket.transaction.service;

import org.bitbucket.transaction.producer.Producer;
import org.bitbucket.transaction.repository.ProductRepository;
import org.bitbucket.transaction.repository.TransactionFeedbackRepository;
import org.bitbucket.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionFeedbackRepository transactionFeedbackRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private Producer kafkaProducer;

    @Override
    public void processTransaction(String transaction) {

        //TODO Transaction pattern matching logic
        //TODO Save incoming transaction to db
        //TODO Transaction Feedback logic

        kafkaProducer.sendMessage(transaction);

    }
}
