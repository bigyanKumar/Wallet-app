package com.masai.repository.bankAccountDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.BankAccount;

@Repository
public interface BankAccountDao extends JpaRepository<BankAccount, Integer>{

}
