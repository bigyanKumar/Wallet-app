package com.masai.customerService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.customerService.Dao.WalletDao;
import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

@Service
public class CustomerServiceImpl implements customerServiceIntr{
	
	@Autowired
	private WalletDao wdo;


	public Customer createAcc(Customer cs)throws CostumerNotFoundException  {
		// TODO Auto-generated method stub
		Optional<Customer> opt=wdo.findById(cs.getMobileNumber());
		
		if(opt.isPresent()){
			throw new CostumerNotFoundException("Customer is present already with this mobile number : "+cs.getMobileNumber());
		}
		return wdo.save(cs);
	}


	@Override
	public Customer showBlacnce(String mobile) throws CostumerNotFoundException {
		System.out.println("2"+mobile);
		
		return wdo.findById(mobile).orElseThrow(()-> new CostumerNotFoundException("Customer not found with this : "+mobile));
		
		//return wdo.findById(mobile).orElseThrow(()->new );
	}

}
