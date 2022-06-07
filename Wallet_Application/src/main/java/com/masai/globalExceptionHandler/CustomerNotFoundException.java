package com.masai.globalExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CustomerNotFoundException(String msg) {
		super(msg);
	}

}
