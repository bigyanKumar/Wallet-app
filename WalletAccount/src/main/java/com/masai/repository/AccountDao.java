package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account,Integer> {
	public List<Account> findBywalletId(Integer walletId);

}
