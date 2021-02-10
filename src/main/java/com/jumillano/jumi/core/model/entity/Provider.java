package com.jumillano.jumi.core.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "providers")
public class Provider {

    @Id
    private String id;
    private String name;
    private String document;
    private String address;
    private String email;
    private Integer phone;
    private Integer cellPhone;
    private Location location;

    public Provider() {
    }

    public Provider(String id, String name, String document, String address, String email, Integer phone, Integer cellPhone, Location location) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Integer cellPhone) {
        this.cellPhone = cellPhone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
