package com.jumillano.jumi.core.model.entity;

import java.util.Date;

public class Person {

    protected String name;
    protected String lastName;
    protected String document;
    protected Date birthDay;
    protected String email;
    protected Integer phone;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Person(String name, String email, Integer phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Person(String name, String lastName, String document, Date birthDay, String email) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.birthDay = birthDay;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
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
}
