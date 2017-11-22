package org.bitbucket.transaction.service;

import org.bitbucket.transaction.event.ReceivedTransactionEvent;

public interface TransactionService {

    void processTransaction(ReceivedTransactionEvent receivedTransactionEvent);
}
