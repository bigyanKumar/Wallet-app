package com.masai.service.customerService;

import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

public interface customerServiceIntr {
	
	public Customer createAcc(Customer cs)throws CostumerNotFoundException;
	
	public Customer showBlacnce(String mobile)throws CostumerNotFoundException;

}
