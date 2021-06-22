package com.jumillano.jumi.core.model.entity;

import com.jumillano.jumi.core.model.enums.PaymentMethod;
import com.jumillano.jumi.core.model.enums.Status;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Document(collection = "events")
public class Event {

    @Id
    private String id;

    @DBRef
    private Provider provider;
    private Double amount;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date start;
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date end;
    private String observation;
    private List<String> file;
    private Status status;
    private PaymentMethod paymentMethod;
    private String dateStart;
    private boolean isProcessed;

    @DBRef
    private User user;

    public Event() {
    }

    public Event(String id, Provider provider, Double amount, Date start, Date end, String observation, List<String> file, Status status, PaymentMethod paymentMethod, boolean isProcessed) {
        this.id = id;
        this.provider = provider;
        this.amount = amount;
        this.start = start;
        this.end = end;
        this.observation = observation;
        this.file = file;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.isProcessed = isProcessed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
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

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getDateStart() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            dateStart = sdf.format((new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",java.util.Locale.ENGLISH)).parse(start.toString()));
        } catch (ParseException e){
            e.printStackTrace();
        }
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setIsProcessed(boolean processed) {
        this.isProcessed = processed;
    }
}
