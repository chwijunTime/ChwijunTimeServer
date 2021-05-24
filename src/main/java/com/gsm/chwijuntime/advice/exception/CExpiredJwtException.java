package com.gsm.chwijuntime.advice.exception;

public class CExpiredJwtException extends RuntimeException {
    public CExpiredJwtException(String msg, Throwable t) {
        super(msg, t);
    }

    public CExpiredJwtException(String msg) {
        super(msg);
    }

    public CExpiredJwtException() {
        super();
    }
}
