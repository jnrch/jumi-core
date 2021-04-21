package com.jumillano.jumi.core.model.entity;

import com.jumillano.jumi.core.model.enums.Status;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;
import java.util.List;

@Document(collection = "events")
public class Event {

    @Id
    private String id;
    private String provider;
    private Double amount;
    private Date start;
    private Date end;
    private String observation;
    private List<String> file;
    private Status status;

    @DBRef
    private User user;

    public Event() {
    }

    public Event(String id, String provider, Double amount, Date start, Date end, String observation, List<String> file, Status status) {
        this.id = id;
        this.provider = provider;
        this.amount = amount;
        this.start = start;
        this.end = end;
        this.observation = observation;
        this.file = file;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public List<String> getFile() {
        return file;
    }

    public void setFile(List<String> file) {
        this.file = file;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
