package com.masai.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class GLobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomerError> myMANVExceptionHandler(MethodArgumentNotValidException me) {
		CustomerError err=new CustomerError(LocalDateTime.now(),"Validation Error",me.getBindingResult().getFieldError().getDefaultMessage());
	return new ResponseEntity<>(err,HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<CustomerError> myExpHandler(Exception ie,WebRequest wr) {
		CustomerError er = new CustomerError(LocalDateTime.now(),ie.getMessage(),wr.getDescription(false));
	return new ResponseEntity<CustomerError>(er,HttpStatus.BAD_REQUEST);	}
}