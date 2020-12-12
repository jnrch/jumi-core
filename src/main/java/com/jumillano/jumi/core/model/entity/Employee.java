package com.jumillano.jumi.core.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    private Long fileNumber;
    private String name;
    private String lastName;
    private String birthDay;
    private String address;
    private Integer phone;
    private Integer cellPhone;
    private Location location;
    private Boolean enabled;

    public Employee() {
    }

    public Employee(Long fileNumber, String name, String lastName, String birthDay, String address, Integer phone, Integer cellPhone, Location location, Boolean enabled) {
        this.fileNumber = fileNumber;
        this.name = name;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.location = location;
        this.enabled = enabled;
    }

    public Long getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Long fileNumber) {
        this.fileNumber = fileNumber;
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

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
