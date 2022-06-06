package com.masai.repository.transactionDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Transaction;

public interface TransactionDao  extends JpaRepository<Transaction, Integer> {

}
