package com.jumillano.jumi.core.model.dto.response;

import java.util.List;

public class JwtResponse {

    private String token;
    private String type = "Bearer";
    private String id;
    private String name;
    private String email;
    private List<String> roles;
    private boolean ok;

    public JwtResponse(String token, String id, String name, String email, List<String> roles, boolean ok) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.ok = ok;
    }

    public JwtResponse(String id, String name, List<String> roles, String token) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
}
