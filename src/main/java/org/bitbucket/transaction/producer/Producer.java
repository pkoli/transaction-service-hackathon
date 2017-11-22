package org.bitbucket.transaction.producer;

import org.bitbucket.transaction.event.AnalyseTransactionEvent;

public interface Producer {

    void sendEvent(AnalyseTransactionEvent event);
}
