package com.masai.service.loginService;

import com.masai.DTO.CustomerDTO;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

public interface LoginService {
	
	public UserSession loginService(CustomerDTO cDto) throws CostumerNotFoundException;
	
	public String logoutService(String key);
	
}
