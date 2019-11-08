package com.jinzhun.common.exception;

public class UnexpectedException extends RuntimeException {

    private static final long serialVersionUID = 6610083281801529147L;

    public UnexpectedException(Exception e) {
        super(e);
    }
}
