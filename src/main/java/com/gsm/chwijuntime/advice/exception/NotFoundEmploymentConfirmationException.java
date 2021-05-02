package com.gsm.chwijuntime.advice.exception;

public class NotFoundEmploymentConfirmationException extends RuntimeException {
    public NotFoundEmploymentConfirmationException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundEmploymentConfirmationException(String msg) {
        super(msg);
    }

    public NotFoundEmploymentConfirmationException() {
        super();
    }
}
