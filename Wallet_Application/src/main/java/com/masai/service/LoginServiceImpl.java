package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.LoginDTO;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private CustomerDao cusD;
	@Autowired
	private UserSessionDao userDao;

	@Override
	public UserSession loginService(LoginDTO cDto) throws CustomerNotFoundException {
		
		Optional<UserSession> user = userDao.findById(cDto.getMobileNumber());
		
		if(user.isPresent()) {
			
			LocalDateTime prev=user.get().getDateTime();
			LocalDateTime date=LocalDateTime.now();
			
			if(prev.getDayOfMonth()==date.getDayOfMonth())		
			return user.get();
			else {
				userDao.delete(user.get());
				loginService(cDto);
			}
		}
		Optional<Customer> customer=cusD.findById(cDto.getMobileNumber());
		customer.orElseThrow(()->new CustomerNotFoundException("User not found") );	
		
		
			if(!customer.get().getPassword().equals(cDto.getPassword()))
				throw new CustomerNotFoundException("Password Doesn't Mathch");
			
			
			
		return userDao.save(new UserSession(cDto.getMobileNumber(),LocalDateTime.now(),RandomString.make(10)));
		
	}

	@Override
	public String logoutService(String key) {
		
		UserSession user=userDao.findByUuid(key);
		if(user!=null) {
		 userDao.delete(user);  
		return "Successfully Logged out. thank you!";
		}else {
			throw new CustomerNotFoundException("User not Fount in our session");
		}
	}

}
