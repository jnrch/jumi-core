package com.jumillano.jumi.core.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "locations")
public class Location {

    @Id
    private ObjectId id;
    private String description;

    public Location() {
    }

    public Location(ObjectId id, String description) {
        this.id = id;
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
