package com.masai.Exception;

public class UserAlreadyExistWithMobileNumber extends RuntimeException {

	public UserAlreadyExistWithMobileNumber() {
		
	}
	
	public UserAlreadyExistWithMobileNumber(String message) {
		super(message);
	}
}
