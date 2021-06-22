package com.jumillano.jumi.core.exceptions;

public class ResourceNotFoundException extends RestException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String body) {
        super(new RestResponse(404, "The resource you are trying to access does not exist.", body));
    }

    public ResourceNotFoundException() {
        super(new RestResponse(404, "The resource you are trying to access does not exist."));
    }
}
