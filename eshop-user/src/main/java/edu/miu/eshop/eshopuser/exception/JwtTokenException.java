package edu.miu.eshop.eshopuser.exception;


public class JwtTokenException extends RuntimeException {
    private String errorMessage;

    public JwtTokenException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
