package com.jumillano.jumi.core.model;

public class Provider {

    private Long id;
    private String name;
    private String document;
    private String address;
    private String email;
    private Integer phone;
    private Integer cellphone;
    private Location location;

    public Provider() {
    }

    public Provider(Long id, String name, String document, String address, String email, Integer phone, Integer cellphone, Location location) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.cellphone = cellphone;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getCellphone() {
        return cellphone;
    }

    public void setCellphone(Integer cellphone) {
        this.cellphone = cellphone;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
