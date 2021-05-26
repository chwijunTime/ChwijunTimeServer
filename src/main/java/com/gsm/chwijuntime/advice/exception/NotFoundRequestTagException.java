package com.gsm.chwijuntime.advice.exception;

public class NotFoundRequestTagException extends RuntimeException {
    public NotFoundRequestTagException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundRequestTagException(String msg) {
        super(msg);
    }

    public NotFoundRequestTagException() {
        super();
    }
}
