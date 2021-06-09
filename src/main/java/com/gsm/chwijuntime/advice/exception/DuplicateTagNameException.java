package com.gsm.chwijuntime.advice.exception;

public class DuplicateTagNameException extends RuntimeException {
    public DuplicateTagNameException(String msg, Throwable t) {
        super(msg, t);
    }

    public DuplicateTagNameException(String msg) {
        super(msg);
    }

    public DuplicateTagNameException() {
        super();
    }
}
