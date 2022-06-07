package com.masai.service;

import java.util.List;

import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface customerServiceIntr {
	
	public Customer createAcc(Customer cs)throws CustomerNotFoundException;
	
	public Customer showBlacnce(String mobile)throws CustomerNotFoundException;
	
	public Customer depositAmount(String mobile, Double amount) throws CustomerNotFoundException;
	
	public String fundTransfer(String mobileNo,String targetMobileNo, Double amount)throws CustomerNotFoundException;
	
	public List<Customer> getListCustomer() throws CustomerNotFoundException ;
	
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	
	public Customer addMoney(Wallet wallet, Double amount) throws CustomerNotFoundException;

}
