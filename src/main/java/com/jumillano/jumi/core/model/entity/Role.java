package com.jumillano.jumi.core.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    private com.jumillano.jumi.core.model.enums.Role name;

    public Role() {
    }

    public Role(String id, com.jumillano.jumi.core.model.enums.Role name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public com.jumillano.jumi.core.model.enums.Role getName() {
        return name;
    }

    public void setName(com.jumillano.jumi.core.model.enums.Role name) {
        this.name = name;
    }
}
