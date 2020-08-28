package com.ausy_technologies.demospring.Exceptions;

public class ErrorResponse extends RuntimeException {

    private String errorMessage;
    private int errorId;
    public ErrorResponse(){

    }

    public ErrorResponse(String errorMessage, int errorId) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorId = errorId;
    }

    public ErrorResponse(Throwable cause, String errorMessage, int errorId) {
        super(errorMessage, cause);
        this.errorMessage = errorMessage;
        this.errorId = errorId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorId() {
        return errorId;
    }
}
