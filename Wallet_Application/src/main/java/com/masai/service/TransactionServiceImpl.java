package com.masai.service;

import com.masai.DTO.Date;
import com.masai.entity.Customer;
import com.masai.entity.Transaction;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionServiceIntr {

    @Autowired
    private UserSessionDao user;
    @Autowired
    private CustomerDao customer;


    @Override
    public List<Transaction> getTransaction(String key) throws CustomerNotFoundException {

        Optional<Customer> cust=customer.findById(user.findByUuid(key).getMobile());

        List<Transaction> tran=cust.get().getWallet().getTran();
        System.out.println(tran);
        return tran;
    }
    @Override
    public List<Transaction> getTransactionByDate(Date date, String key) throws CustomerNotFoundException {
    	
    	Optional<Customer> cust =customer.findById(user.findByUuid(key).getMobile());
    	 
    	List<Transaction> tran=cust.get().getWallet().getTran();
    	
    	List<Transaction> upend=new ArrayList<>();
    	
    	System.out.println(date.getDateFrom().getYear());
    	System.out.println(date.getDateFrom().getMonthValue());
    	System.out.println(date.getDateFrom().getDayOfMonth());
    	
    	for(Transaction tr:tran) {
    		if(tr.getDateTime().getYear()>=date.getDateFrom().getYear() &&
    				tr.getDateTime().getMonthValue()>=date.getDateFrom().getMonthValue() 
    				&& tr.getDateTime().getDayOfMonth()>=date.getDateFrom().getDayOfMonth() &&
    				tr.getDateTime().getYear()<=date.getDateTo().getYear() &&
    				tr.getDateTime().getMonthValue()<=date.getDateTo().getMonthValue() 
    				&& tr.getDateTime().getDayOfMonth()<=date.getDateTo().getDayOfMonth()) {
    			
    			upend.add(tr);
    			
    		}
    		
    	}
    	
    	
        return upend;
    }
}