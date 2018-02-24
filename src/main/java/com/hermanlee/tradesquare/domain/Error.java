package com.hermanlee.tradesquare.domain;

public class Error {

    private String message;

    public Error(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
