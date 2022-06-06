package com.masai.service.transactionService;

import com.masai.DTO.Date;
import com.masai.entity.Transaction;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import java.util.List;

public interface TransactionServiceIntr {

    public List<Transaction> getTransaction(String key) throws CostumerNotFoundException;

    public List<Transaction> getTransactionByDate(Date date,String key)throws CostumerNotFoundException;
}
