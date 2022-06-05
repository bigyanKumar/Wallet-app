package com.masai.service;

import com.masai.model.CustomerUserSession;

public interface GetCurrentLoginUserSessionDetailsInterface {

	public  CustomerUserSession getCurrentSession(String key);
}
