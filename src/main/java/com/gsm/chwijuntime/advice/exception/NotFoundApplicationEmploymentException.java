package com.gsm.chwijuntime.advice.exception;

public class NotFoundApplicationEmploymentException extends RuntimeException {

    public NotFoundApplicationEmploymentException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundApplicationEmploymentException(String msg) {
        super(msg);
    }

    public NotFoundApplicationEmploymentException() {
        super();
    }

}
