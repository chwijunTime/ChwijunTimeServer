package com.gsm.chwijuntime.advice.exception;

public class NotFoundContractingCompanyException extends RuntimeException {

    public NotFoundContractingCompanyException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundContractingCompanyException(String msg) {
        super(msg);
    }

    public NotFoundContractingCompanyException() {
        super();
    }

}
