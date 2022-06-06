package com.masai.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AccountException;
import com.masai.model.Account;
import com.masai.repository.AccountDao;


@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao aDao;
	
	
	
	
	
	
	@Override
	public Account addAcount(Account account) {
		return aDao.save(account);
	}

	@Override
	public List<Account> getAccountsByWalletId(Integer walletId) throws AccountException {
		List<Account> accounts=aDao.findBywalletId(walletId);
		if(accounts.size()>0) {
			return accounts;
		}
		else
			throw new AccountException("Account available please add account with your wallet");
	}
	
	

	@Override
	public Account deleteAccountByAccountNo(Integer accountId) {
		Account existingAccount= aDao.findById(accountId).orElseThrow( () -> new AccountException("Account does not exist with ") );
		
		aDao.delete(existingAccount);
		
		return existingAccount;
	}

	@Override
	public Account getAccountByAccountNo(Integer accountNo) {
		Account a=aDao.findById(accountNo).orElseThrow( () -> new AccountException("Account does not exist with ") );
		return a;
	}




}





