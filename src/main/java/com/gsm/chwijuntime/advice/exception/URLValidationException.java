package com.gsm.chwijuntime.advice.exception;

public class URLValidationException extends RuntimeException {
    public URLValidationException(String msg, Throwable t) {
        super(msg, t);
    }

    public URLValidationException(String msg) {
        super(msg);
    }

    public URLValidationException() {
        super();
    }
}
