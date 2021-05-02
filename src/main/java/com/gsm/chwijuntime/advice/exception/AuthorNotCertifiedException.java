package com.gsm.chwijuntime.advice.exception;

public class AuthorNotCertifiedException extends RuntimeException {
    public AuthorNotCertifiedException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthorNotCertifiedException(String msg) {
        super(msg);
    }

    public AuthorNotCertifiedException() {
        super();
    }
}
