package com.jumillano.jumi.core.model.entity;

import java.util.Date;

public class Person {

    protected String name;
    protected String lastName;
    protected String document;
    protected Date dayOfBirth;

    public Person(String name, String lastName, String document, Date dayOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.document = document;
        this.dayOfBirth = dayOfBirth;
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

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }
}
