package com.gsm.chwijuntime.advice.exception;

public class NoTokenANDTokenWrongException extends RuntimeException {

    public NoTokenANDTokenWrongException(String msg, Throwable t) {
        super(msg, t);
    }

    public NoTokenANDTokenWrongException(String msg) {
        super(msg);
    }

    public NoTokenANDTokenWrongException() {
        super();
    }

}
