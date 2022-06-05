package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private CustomerLogInImpl customerLogIn;
	
	
	@PostMapping("/login")
	public String lopInCustomer(@RequestParam("mobileNo") long mobileNo,@RequestParam("password") String password)
	{
		return  customerLogIn.logIntoAccount(mobileNo,password);
	}
	
	 
	@PatchMapping("logout")
	public String logOutCustomer(@RequestParam(required =false) String key)
	{
		return customerLogIn.logOutFromAccount(key);
	}
}



