package com.gsm.chwijuntime.advice.exception;

public class NotFoundConsultingAdminException extends RuntimeException {
    public NotFoundConsultingAdminException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundConsultingAdminException(String msg) {
        super(msg);
    }

    public NotFoundConsultingAdminException() {
        super();
    }
}
