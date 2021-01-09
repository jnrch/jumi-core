package com.jumillano.jumi.core.model.entity;

import java.util.Date;

public class Person {

    private String name;
    private String lastName;
    private String document;
    private Date birthDay;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Person(String name, String lastName, String document, Date birthDay) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.birthDay = birthDay;
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
}
