package com.gsm.chwijuntime.advice.exception;

public class EmailNotFoundException extends RuntimeException {

    public EmailNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public EmailNotFoundException(String msg) {
        super(msg);
    }

    public EmailNotFoundException() {
        super();
    }

}
