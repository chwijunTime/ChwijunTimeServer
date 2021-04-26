package com.gsm.chwijuntime.advice.exception;

public class RequestAlreadyRejectedException extends RuntimeException {
    public RequestAlreadyRejectedException(String msg, Throwable t) {
        super(msg, t);
    }

    public RequestAlreadyRejectedException(String msg) {
        super(msg);
    }

    public RequestAlreadyRejectedException() {
        super();
    }
}
