package com.masai.service;

import com.masai.DTO.LoginDTO;

import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface LoginService {
	
	public UserSession loginService(LoginDTO cDto) throws CustomerNotFoundException;
	
	public String logoutService(String key);
	
}
