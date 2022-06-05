package com.masai.service;

public interface CustomerLogIn {

	
	public String logIntoAccount (long mobileNo,String password);
	
	public String logOutFromAccount(String key);
}
