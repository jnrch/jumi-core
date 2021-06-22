package com.jumillano.jumi.core.exceptions;

public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private RestResponse response;

    public RestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RestException(RestResponse restResponse) {
        super(restResponse.getStatusCode() + ":" + restResponse.getReason());
        this.response = restResponse;
    }

    public RestException(RestResponse restResponse, Throwable cause) {
        super(cause);
        this.response = restResponse;
    }

    public RestResponse getResponse() {
        return this.response;
    }
}
