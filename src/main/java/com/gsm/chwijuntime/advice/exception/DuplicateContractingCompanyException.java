package com.gsm.chwijuntime.advice.exception;

public class DuplicateContractingCompanyException extends RuntimeException {
    public DuplicateContractingCompanyException(String msg, Throwable t) {
        super(msg, t);
    }

    public DuplicateContractingCompanyException(String msg) {
        super(msg);
    }

    public DuplicateContractingCompanyException() {
        super();
    }
}
