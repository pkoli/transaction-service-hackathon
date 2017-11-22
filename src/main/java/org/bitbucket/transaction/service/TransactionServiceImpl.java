package org.bitbucket.transaction.service;

import org.bitbucket.transaction.entity.Product;
import org.bitbucket.transaction.entity.Transaction;
import org.bitbucket.transaction.entity.TransactionFeedback;
import org.bitbucket.transaction.event.AnalyseTransactionEvent;
import org.bitbucket.transaction.event.ReceivedTransactionEvent;
import org.bitbucket.transaction.producer.Producer;
import org.bitbucket.transaction.repository.ProductRepository;
import org.bitbucket.transaction.repository.TransactionFeedbackRepository;
import org.bitbucket.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionFeedbackRepository transactionFeedbackRepository;

    @Autowired
    private Producer kafkaProducer;

    @Override
    public void processTransaction(ReceivedTransactionEvent event) {

        Transaction transaction = new Transaction();

        transaction.setCustomerId(String.valueOf(event.getCustomerId()));
        transaction.setTransactionAmount(event.getTransactionAmount());
        transaction.setTransactionDescription(event.getTransactionDescription());
        transaction.setTranscationType(event.getTransactionType());
        transaction.setTransactionDate(event.getTransactionDate());

        transactionRepository.save(transaction);

        Product product = productRepository.findByTransactionTypeAndMerchantName(event.getTransactionType(), event.getMerchantName());

        if(product.getProductId() != null) {

            TransactionFeedback feedback = transactionFeedbackRepository.findByCustomerIdAndProductId(event.getCustomerId(),
                    String.valueOf(product.getProductId()));

            if (!feedback.isAcceptStatus()) {
                feedback.setAcceptStatus(true);
                transactionFeedbackRepository.save(feedback);
                return;
            }
        }

        if(!transactionFeedbackRepository.exists(event.getCustomerId())) {

            AnalyseTransactionEvent analyseTransactionEvent = new AnalyseTransactionEvent();

            event.setCustomerId(event.getCustomerId());
            event.setTransactionAmount(event.getTransactionAmount());
            event.setTransactionDate(event.getTransactionDate());
            event.setTransactionDescription(event.getTransactionDescription());
            event.setTransactionType(event.getTransactionType());

            kafkaProducer.sendEvent(analyseTransactionEvent);

        }

    }
}
