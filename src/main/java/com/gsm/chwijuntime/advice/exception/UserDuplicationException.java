package com.gsm.chwijuntime.advice.exception;

public class UserDuplicationException extends RuntimeException {

    public UserDuplicationException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserDuplicationException(String msg) {
        super(msg);
    }

    public UserDuplicationException() {
        super();
    }

}
