package com.masai.globalExceptionHandler;

public class InsufficientAmountException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InsufficientAmountException() {
		
	}
	
	public InsufficientAmountException(String message) {
		super(message);
	}
}
