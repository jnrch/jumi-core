package com.jumillano.jumi.core.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

@Document(collection = "employees")
public class Employee extends Person{

    @Id
    private ObjectId id;
    private Long fileNumber;
    private String address;
    private Integer phone;
    private Integer cellPhone;
    private Location location;
    private Boolean enabled;

    public Employee() {
    }

    public Employee(ObjectId id, Long fileNumber, String name, String lastName, String document, Date birthDay, String address, Integer phone, Integer cellPhone, Location location, Boolean enabled) {
        super(name,lastName,document,birthDay);
        this.id = id;
        this.fileNumber = fileNumber;
        this.address = address;
        this.phone = phone;
        this.cellPhone = cellPhone;
        this.location = location;
        this.enabled = enabled;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(Long fileNumber) {
        this.fileNumber = fileNumber;
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
