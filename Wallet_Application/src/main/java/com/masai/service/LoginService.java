package com.masai.service;

import com.masai.DTO.CustomerDTO;

import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface LoginService {
	
	public UserSession loginService(CustomerDTO cDto) throws CustomerNotFoundException;
	
	public String logoutService(String key);
	
}
