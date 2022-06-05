package com.masai.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.Exception.UserAlreadyExistWithMobileNumber;
import com.masai.model.Customer;
import com.masai.repository.CustomerDao;
import com.masai.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {


	@Autowired
	private CustomerDao customerDAO;
	
	@Autowired
	private GetCurrentLoginUserSessionDetailsImpl getCurrentLoginUser;
	
	@Override
	public Customer createCustomer(Customer customer) {
		Optional<Customer> opt = customerDAO.findByMobileNo(customer.getMobileNo());
		
		if(opt.isPresent()) {
			throw new UserAlreadyExistWithMobileNumber("User already exist with this mobile number");
		}
		return customerDAO.save(customer);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer deleteCustomer(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomerDetails(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
