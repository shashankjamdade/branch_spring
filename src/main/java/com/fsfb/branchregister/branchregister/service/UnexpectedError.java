package com.fsfb.branchregister.branchregister.service;

public class UnexpectedError extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private String message;

    UnexpectedError(String msg){
        this.message = msg;
    }
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
