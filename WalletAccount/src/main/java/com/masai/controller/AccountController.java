package com.masai.controller;


import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Account;
import com.masai.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService aAccount;
	
	@PostMapping("/addaccount")
	public ResponseEntity<Account> saveAccountHandler(@RequestBody Account account){
		
		Account addAccount= aAccount.addAcount(account);
		return new ResponseEntity<Account>(addAccount,HttpStatus.CREATED);
	}
	@GetMapping("showaccounts/{walletId}")
	public List<Account> getAccountByWalletId(@PathVariable("walletId") Integer walletId){
		return aAccount.getAccountsByWalletId(walletId);
	}
	@DeleteMapping("deleteaccount/{accountNo}")
	public Account deleteAccountHandler(@PathVariable("accountNo") Integer accountNo){
		return aAccount.deleteAccountByAccountNo(accountNo);	
	}
	
	@GetMapping("showaccount/{accountNo}")
	public Account getAccountByaccountNo(@PathVariable("accountNo") Integer accountNo){
		return aAccount.getAccountByAccountNo(accountNo);
	}
}
















