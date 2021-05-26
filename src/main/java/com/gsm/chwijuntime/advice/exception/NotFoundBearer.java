package com.gsm.chwijuntime.advice.exception;

public class NotFoundBearer extends RuntimeException {
    public NotFoundBearer(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundBearer(String msg) {
        super(msg);
    }

    public NotFoundBearer() {
        super();
    }
}
