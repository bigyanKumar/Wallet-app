package com.masai.service.loginService;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.CustomerDTO;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.userSessionDao.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private CustomerDao cusD;
	@Autowired
	private UserSessionDao userDao;

	@Override
	public UserSession loginService(CustomerDTO cDto) throws CostumerNotFoundException {
		
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
		
		Customer cs=customer.get();
		
		if(customer.isPresent()) {
			System.out.println();
			if(!cs.getPassword().equals(cDto.getPassword())) {
				throw new CostumerNotFoundException("Password Doesn't Mathch");
			}
			
		return userDao.save(new UserSession(cDto.getMobileNumber(),LocalDateTime.now(),RandomString.make(10)));
		}else {
			throw new CostumerNotFoundException("User not found");
		}
	}

	@Override
	public String logoutService(String key) {
		
		UserSession user=userDao.findByUuid(key);
		if(user!=null) {
		 userDao.delete(user);  
		return "Successfully Logged out. thank you!";
		}else {
			throw new CostumerNotFoundException("User not Fount in our session");
		}
	}

}
