package com.gsm.chwijuntime.advice.exception;

public class NotFoundCorrectionApply extends RuntimeException {

    public NotFoundCorrectionApply(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundCorrectionApply(String msg) {
        super(msg);
    }

    public NotFoundCorrectionApply() {
        super();
    }

}
