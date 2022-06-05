package com.masai.service.transactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.Customer;
import com.masai.entity.Transaction;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.userSessionDao.UserSessionDao;
@Service
public class TransactionServiceImpl implements TransactionServiceIntr {
	
	@Autowired
	private UserSessionDao user;
	@Autowired
	private CustomerDao customer;
	
	

	@Override
	public List<Transaction> getTransaction(String key) throws CostumerNotFoundException {
		
		Optional<Customer> cust=customer.findById(user.findByUuid(key).getMobile());
		
		List<Transaction> tran=cust.get().getWallet().getTran();
		 
		return tran;
	}

	@Override
	public List<Transaction> getTransactionByDate(LocalDate from, LocalDate to) throws CostumerNotFoundException {
		
		
		
		
		return null;
	}

}
