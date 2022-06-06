package com.masai.repository.bankAccountDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.BankAccount;

public interface BankAccountDao extends JpaRepository<BankAccount, Integer>{

}
