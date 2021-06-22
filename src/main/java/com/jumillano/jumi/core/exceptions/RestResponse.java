package com.jumillano.jumi.core.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.http.StatusLine;

import java.io.Serializable;

public class RestResponse implements Serializable {

    private int statusCode;
    private String reason;
    private String body;

    public RestResponse() {
    }

    public RestResponse(int statusCode, String reason) {
        this(statusCode, reason, reason);
    }

    public RestResponse(int statusCode, String reason, String body) {
        this.statusCode = statusCode;
        this.reason = reason;
        this.body = body;
    }

    public RestResponse(StatusLine status, String body) {
        this.statusCode = status.getStatusCode();
        this.reason = status.getReasonPhrase();
        this.body = body;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @JsonIgnore
    public boolean is20xFamily() {
        switch(this.statusCode) {
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
                return true;
            default:
                return false;
        }
    }

    @JsonIgnore
    public boolean is401Error() {
        return this.statusCode == 401;
    }

    @JsonIgnore
    public boolean is403Error() {
        return this.statusCode == 403;
    }

    @JsonIgnore
    public boolean is404Error() {
        return this.statusCode == 404;
    }

    @JsonIgnore
    public boolean is400Error() {
        return this.statusCode == 400;
    }

    @JsonIgnore
    public boolean is413Error() {
        return this.statusCode == 413;
    }

    @JsonIgnore
    public boolean is422Error() {
        return this.statusCode == 422;
    }

    @JsonIgnore
    public boolean is5xxFamily() {
        return this.statusCode >= 500 && this.statusCode < 600;
    }
}
