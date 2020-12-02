package com.jumillano.jumi.core.model.entity;

import java.util.Date;

public class PaymentSchedule {

    private Long id;
    private Provider provider;
    private Double amount;
    private Date paymentDate;
    private String observations;
    private String attachedDocument;

    public PaymentSchedule() {
    }

    public PaymentSchedule(Long id, Provider provider, Double amount, Date paymentDate, String observations, String attachedDocument) {
        this.id = id;
        this.provider = provider;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.observations = observations;
        this.attachedDocument = attachedDocument;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getAttachedDocument() {
        return attachedDocument;
    }

    public void setAttachedDocument(String attachedDocument) {
        this.attachedDocument = attachedDocument;
    }
}
