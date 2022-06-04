package com.masai.globalExceptionHandler;

public class InsufficientAmountException extends RuntimeException {
	public InsufficientAmountException() {
		
	}
	
	public InsufficientAmountException(String message) {
		super(message);
	}
}
