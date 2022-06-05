package com.masai.service.transactionService;

import java.time.LocalDate;
import java.util.List;

import com.masai.entity.Transaction;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

public interface TransactionServiceIntr {

	public List<Transaction> getTransaction(String key) throws CostumerNotFoundException;
	
	public List<Transaction> getTransactionByDate(LocalDate from , LocalDate to)throws CostumerNotFoundException;
}
