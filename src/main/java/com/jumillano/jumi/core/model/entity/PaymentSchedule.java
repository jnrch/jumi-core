package com.jumillano.jumi.core.model.entity;

import com.jumillano.jumi.core.model.enums.Status;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "paymentsSchedule")
public class PaymentSchedule {

    @Id
    private String id;
    private Provider provider;
    private Double amount;
    private Date paymentDate;
    private String observation;
    private String attachedDocument;
    private Status status;

    public PaymentSchedule() {
    }

    public PaymentSchedule(String id, Provider provider, Double amount, Date paymentDate, String observation, String attachedDocument, Status status) {
        this.id = id;
        this.provider = provider;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.observation = observation;
        this.attachedDocument = attachedDocument;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getAttachedDocument() {
        return attachedDocument;
    }

    public void setAttachedDocument(String attachedDocument) {
        this.attachedDocument = attachedDocument;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
