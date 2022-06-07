package com.masai.service;

import com.masai.DTO.Date;
import com.masai.entity.Transaction;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import java.util.List;

public interface TransactionServiceIntr {

    public List<Transaction> getTransaction(String key) throws CustomerNotFoundException;

    public List<Transaction> getTransactionByDate(Date date,String key)throws CustomerNotFoundException;
}
