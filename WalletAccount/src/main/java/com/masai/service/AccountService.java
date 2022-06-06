package com.masai.service;

import java.util.List;

import com.masai.model.Account;

public interface AccountService {

	public Account addAcount(Account Account);
	
	public List<Account> getAccountsByWalletId(Integer walletId) ;

	public Account deleteAccountByAccountNo(Integer accountId);
	
	public Account getAccountByAccountNo(Integer AccountNo);

}











