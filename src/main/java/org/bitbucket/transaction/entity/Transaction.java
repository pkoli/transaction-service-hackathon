package org.bitbucket.transaction.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Transaction implements Serializable{

    @Id
    private Integer transactionId;

    private String transcationType;

    private String transactionAmount;

    private String customerId;

    private String transcationDescription;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTranscationType() {
        return transcationType;
    }

    public void setTranscationType(String transcationType) {
        this.transcationType = transcationType;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getTranscationDescription() {
        return transcationDescription;
    }

    public void setTranscationDescription(String transcationDescription) {
        this.transcationDescription = transcationDescription;
    }

    public Transaction(){
        //Required by JPA
    }
}
