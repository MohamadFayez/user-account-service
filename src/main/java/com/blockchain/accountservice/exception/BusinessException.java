package com.blockchain.accountservice.exception;

public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected int code;

    protected String reason;

    protected BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    protected BusinessException(String message, int code, String reason) {
        super(message);
        this.code = code;
        this.reason = reason;
    }

}

