package com.gsm.chwijuntime.advice.exception;

public class RequestAlreadyApprovedException extends RuntimeException {
    public RequestAlreadyApprovedException(String msg, Throwable t) {
        super(msg, t);
    }

    public RequestAlreadyApprovedException(String msg) {
        super(msg);
    }

    public RequestAlreadyApprovedException() {
        super();
    }
}
