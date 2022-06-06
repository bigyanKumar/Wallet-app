package com.masai.repository.transactionDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Transaction;
@Repository
public interface TransactionDao  extends JpaRepository<Transaction, Integer> {

}
