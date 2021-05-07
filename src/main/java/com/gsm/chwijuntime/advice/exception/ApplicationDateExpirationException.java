package com.gsm.chwijuntime.advice.exception;

public class ApplicationDateExpirationException extends RuntimeException {
    public ApplicationDateExpirationException(String msg, Throwable t) {
        super(msg, t);
    }

    public ApplicationDateExpirationException(String msg) {
        super(msg);
    }

    public ApplicationDateExpirationException() {
        super();
    }
}
