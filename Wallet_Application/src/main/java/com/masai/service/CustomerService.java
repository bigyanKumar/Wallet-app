package com.masai.service;

import com.masai.model.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer, String key);
	
	public Customer deleteCustomer(String key);
	
	public Customer getCustomerDetails(String key);
}
