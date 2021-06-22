package com.jumillano.jumi.core.model.entity;

public class SalesContact {

    private String name;
    private String email;
    private Integer phone;

    public SalesContact() {
    }

    public SalesContact(String name, String email, Integer phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    /*public SalesContact(String name, String email, Integer phone) {
        super(name, email, phone);
    }*/
}
