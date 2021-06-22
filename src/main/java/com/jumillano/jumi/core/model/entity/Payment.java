package com.jumillano.jumi.core.model.entity;

import com.jumillano.jumi.core.model.enums.PaymentMethod;

public class Payment {
    private PaymentMethod name;
    private Double total;

    public Payment() {

    }

    public Payment(PaymentMethod name, Double total) {
        this.name = name;
        this.total = total;
    }

    public PaymentMethod getName() {
        return name;
    }

    public void setName(PaymentMethod name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
