package com.gsm.chwijuntime.advice.exception;

public class RedundantApplicationException extends RuntimeException {
    public RedundantApplicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public RedundantApplicationException(String msg) {
        super(msg);
    }

    public RedundantApplicationException() {
        super();
    }
}
