package com.gsm.chwijuntime.advice.exception;

public class NotFoundPortfolioException extends RuntimeException {
    public NotFoundPortfolioException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundPortfolioException(String msg) {
        super(msg);
    }

    public NotFoundPortfolioException() {
        super();
    }
}
