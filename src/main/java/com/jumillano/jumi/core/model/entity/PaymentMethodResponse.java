package com.jumillano.jumi.core.model.entity;

import java.util.Collection;
import java.util.Date;

public class PaymentMethodResponse {
    private Date end;
    private Date start;
    private Collection<PaymentResponse> payments;
    private Double bigTotal;

    public PaymentMethodResponse() {
    }

    public PaymentMethodResponse(Date end, Date start, Collection<PaymentResponse> payments, Double bigTotal) {
        this.end = end;
        this.start = start;
        this.payments = payments;
        this.bigTotal = bigTotal;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Collection<PaymentResponse> getPayments() {
        return payments;
    }

    public void setPayments(Collection<PaymentResponse> payments) {
        this.payments = payments;
    }

    public Double getBigTotal() {
        return bigTotal;
    }

    public void setBigTotal(Double bigTotal) {
        this.bigTotal = bigTotal;
    }
}
