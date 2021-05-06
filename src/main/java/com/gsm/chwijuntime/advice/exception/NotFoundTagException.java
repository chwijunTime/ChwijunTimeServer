package com.gsm.chwijuntime.advice.exception;

public class NotFoundTagException extends RuntimeException {
    public NotFoundTagException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundTagException(String msg) {
        super(msg);
    }

    public NotFoundTagException() {
        super();
    }
}
