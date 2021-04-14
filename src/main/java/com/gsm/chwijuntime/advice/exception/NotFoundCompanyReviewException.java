package com.gsm.chwijuntime.advice.exception;

public class NotFoundCompanyReviewException extends RuntimeException {

    public NotFoundCompanyReviewException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundCompanyReviewException(String msg) {
        super(msg);
    }

    public NotFoundCompanyReviewException() {
        super();
    }

}
