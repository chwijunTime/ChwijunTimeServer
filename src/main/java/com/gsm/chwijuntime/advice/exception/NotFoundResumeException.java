package com.gsm.chwijuntime.advice.exception;

public class NotFoundResumeException extends RuntimeException {
    public NotFoundResumeException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundResumeException(String msg) {
        super(msg);
    }

    public NotFoundResumeException() {
        super();
    }
}
