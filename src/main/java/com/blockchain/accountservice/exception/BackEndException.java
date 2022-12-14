package com.blockchain.accountservice.exception;

public class BackEndException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    protected int code;

    protected String reason;

    protected BackEndException() {
        super();
    }

    public BackEndException(String message) {
        super(message);
    }

    protected BackEndException(String message, int code, String reason) {
        super(message);
        this.code = code;
        this.reason = reason;
    }

}
