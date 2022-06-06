package com.masai.globalExceptionHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CostumerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CostumerNotFoundException(String msg) {
		super(msg);
	}

}
