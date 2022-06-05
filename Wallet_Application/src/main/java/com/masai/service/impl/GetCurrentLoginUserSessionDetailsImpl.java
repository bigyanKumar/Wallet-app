package com.masai.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.masai.model.CustomerUserSession;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;
import com.masai.service.GetCurrentLoginUserSessionDetailsInterface;


@Component
public class GetCurrentLoginUserSessionDetailsImpl implements GetCurrentLoginUserSessionDetailsInterface {

	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private CustomerDao customerDAO;
	
	@Override
	public CustomerUserSession getCurrentSession(String key) {
		Optional<CustomerUserSession>  opt =sessionDao.findByUuid(key);
		if(!opt.isPresent())
		{
			System.out.println("Unanthorized");
		}
		return opt.get();
	}

}