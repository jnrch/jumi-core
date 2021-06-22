package com.jumillano.jumi.core.model.entity;

public class PaymentResponse {

    private String name;
    private Double total;

    public PaymentResponse() {
    }

    public PaymentResponse(String name, Double total) {
        this.name = name;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
