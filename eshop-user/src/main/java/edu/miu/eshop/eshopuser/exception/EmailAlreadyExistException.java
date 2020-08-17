package edu.miu.eshop.eshopuser.exception;

public class EmailAlreadyExistException extends Exception {
	private String errorMessage;
	public EmailAlreadyExistException(String errorMessage) { this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}