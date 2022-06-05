package com.masai.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.NotFoundException;
import com.masai.model.Customer;
import com.masai.model.CustomerUserSession;
import com.masai.repository.CustomerDao;
import com.masai.repository.SessionDao;
import com.masai.service.CustomerLogIn;


@Service
public class CustomerLogInImpl implements CustomerLogIn {
	
	@Autowired
	private CustomerDao customerDAO;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private GetCurrentLoginUserSessionDetailsImpl getCurrentLoginUser;

	private int keySize = 10;
	
	@Override
	public String logIntoAccount(long mobileNo,String password) {
		Optional<Customer> opt =customerDAO.findByMobileNo(mobileNo);
		
		if(!opt.isPresent())
		{
			return "Please enter Correct Mobile Number";
		}
		
		Customer newCustomer1 = opt.get();
		long customerId1 =newCustomer1.getCustomerId();
		Optional<CustomerUserSession> currentUserOptional1=sessionDao.findByCustomerId(customerId1);
		
		if(currentUserOptional1.isPresent())
		{
			return "user already Login";
		}
		
		if(newCustomer1.getPassword().equals(password))
		{
			String key = getRandomKey(keySize);
			
			CustomerUserSession currentUserSession =new CustomerUserSession(newCustomer1.getCustomerId(), key,LocalDateTime.now());
			sessionDao.save(currentUserSession);
			
			return key;
		}
		else 
		{
			return "Please enter valid Password";
		}
	}

	@Override
	public String logOutFromAccount(String key) {
Optional<CustomerUserSession> currentUserOptional = sessionDao.findByUuid(key);
		
		if(!currentUserOptional.isPresent()) {
			throw new NotFoundException("User is not logged in with this number");
		}
		
		CustomerUserSession currentUserSession = getCurrentLoginUser.getCurrentSession(key);
		sessionDao.delete(currentUserSession);
		
		return "Logged Out...";
	}
	
	private String getRandomKey(int size) {
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

//		int n=AlphaNumericString.length();
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(size);

		for (int i = 0; i < size; i++) {

				// generate a random number between
				// 0 to AlphaNumericString variable length
			int index= (int)(AlphaNumericString.length()* Math.random());
			
			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}
		
		return sb.toString();
	}
	
	
	
}
