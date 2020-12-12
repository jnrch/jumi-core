package com.jumillano.jumi.core.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    private Long id;
    private String description;

    public Role() {
    }

    public Role(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
