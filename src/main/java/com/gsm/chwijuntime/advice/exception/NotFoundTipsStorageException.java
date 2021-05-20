package com.gsm.chwijuntime.advice.exception;

public class NotFoundTipsStorageException extends RuntimeException {

    public NotFoundTipsStorageException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundTipsStorageException(String msg) {
        super(msg);
    }

    public NotFoundTipsStorageException() {
        super();
    }

}
