package com.dynamicheart.raven.controller.common.model;

public class GenericResponseBody {
    private String message;

    public GenericResponseBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
