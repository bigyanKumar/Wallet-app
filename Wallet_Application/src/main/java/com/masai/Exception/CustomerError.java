package com.masai.Exception;

import java.time.LocalDateTime;

public class CustomerError {

	private LocalDateTime localdatetime;
	private String message;
	private String details;
	public CustomerError(LocalDateTime localdatetime, String message, String details) {
		super();
		this.localdatetime = localdatetime;
		this.message = message;
		this.details = details;
	}
	public LocalDateTime getLocaldatetime() {
		return localdatetime;
	}
	public void setLocaldatetime(LocalDateTime localdatetime) {
		this.localdatetime = localdatetime;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
}
