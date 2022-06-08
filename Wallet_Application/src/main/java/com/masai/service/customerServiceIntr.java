package com.masai.service;

import com.masai.DTO.AddMoneyDTO;
import com.masai.DTO.CustomerDTO;
import com.masai.DTO.DepositDTO;
import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface customerServiceIntr {
	
	public Customer createAcc(Customer cs)throws CustomerNotFoundException;
	
	public CustomerDTO showBlacnce(String mobile)throws CustomerNotFoundException;
	
	public Customer depositAmount(DepositDTO deposit, String mobile) throws CustomerNotFoundException;
	
//	public String fundTransfer(String mobileNo,String targetMobileNo, Double amount)throws CustomerNotFoundException;
	
	public Customer getListCustomer(String key) throws CustomerNotFoundException ;
	
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException;
	
	public Customer addMoney(AddMoneyDTO addmoney,String mobile) throws CustomerNotFoundException;

}
