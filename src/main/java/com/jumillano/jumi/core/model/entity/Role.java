package com.jumillano.jumi.core.model.entity;

import com.jumillano.jumi.core.model.enums.RoleType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;
    private RoleType name;

    public Role() {
    }

    public Role(String id, RoleType name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RoleType getName() {
        return name;
    }

    public void setName(RoleType name) {
        this.name = name;
    }
}
