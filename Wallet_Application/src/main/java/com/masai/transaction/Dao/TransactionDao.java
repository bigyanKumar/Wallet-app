package com.masai.transaction.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{

}
