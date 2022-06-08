package com.masai.service;

import java.util.List;

import com.masai.DTO.BankAccountDTO;
import com.masai.entity.BankAccount;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface BankAccountService {
	
	public String createBankAccount(BankAccount bankAccount);
	
	public BankAccount getAccountByAccountNumber(Integer accountNumber) throws CustomerNotFoundException;
	
	public List<BankAccountDTO> getAccountByWalletId(Integer walletId)throws CustomerNotFoundException;
	
	public String removeAccount(Integer accountNo)throws CustomerNotFoundException;
	
	public BankAccount moneyTransfer(Integer accountNumber1,Integer accountNumber2, double balance,Wallet wallet);
	

}
