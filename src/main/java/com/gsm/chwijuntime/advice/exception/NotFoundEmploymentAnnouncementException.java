package com.gsm.chwijuntime.advice.exception;

public class NotFoundEmploymentAnnouncementException extends RuntimeException {

    public NotFoundEmploymentAnnouncementException(String msg, Throwable t) {
        super(msg, t);
    }

    public NotFoundEmploymentAnnouncementException(String msg) {
        super(msg);
    }

    public NotFoundEmploymentAnnouncementException() {
        super();
    }

}
