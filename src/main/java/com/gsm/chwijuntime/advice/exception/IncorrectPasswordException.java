package com.gsm.chwijuntime.advice.exception;

public class IncorrectPasswordException extends RuntimeException {

    public IncorrectPasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public IncorrectPasswordException(String msg) {
        super(msg);
    }

    public IncorrectPasswordException() {
        super();
    }

}
