package com.masai.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.BankAccountService.BankAccountServiceImpl;
import com.masai.entity.BankAccount;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.userSessionDao.UserSessionDao;

@RestController
public class BankAccountController{
	
	@Autowired
	BankAccountServiceImpl bankService;
	
	@Autowired
	UserSessionDao userDao;
	
	@Autowired
	CustomerDao cusDao;
	
	@PostMapping("/banks")
	public ResponseEntity<String> createNewAccount(@RequestParam("key") String key,@RequestBody BankAccount bankAccount)
	{
		UserSession user=userDao.findByUuid(key);
		if(user==null) {
			throw new CustomerNotFoundException("You are not authoraised person please login first.");
		}
		LocalDateTime prev=user.getDateTime();
		LocalDateTime date=LocalDateTime.now();
		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
			userDao.delete(user);
			throw new CustomerNotFoundException("Your session is expired please login again");
		}
		Optional<Customer> opt = cusDao.findById(user.getMobile());
		
		bankAccount.setWallet(opt.get().getWallet());

		return new ResponseEntity<String>(bankService.createBankAccount(bankAccount),HttpStatus.CREATED);
	}
	
	@GetMapping("/banks") 
	public ResponseEntity<BankAccount> getAccountByAccountNumber(@RequestParam("key") String key,@RequestParam("accountNumber") Integer accountNumber)
	{
		UserSession user=userDao.findByUuid(key);
		if(user==null) {
			throw new CustomerNotFoundException("You are not authoraised person please login first.");
		}
		LocalDateTime prev=user.getDateTime();
		LocalDateTime date=LocalDateTime.now();
		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
			userDao.delete(user);
			throw new CustomerNotFoundException("Your session is expired please login again");
		}
		
		return new ResponseEntity<BankAccount>(bankService.getAccountByAccountNumber(accountNumber),HttpStatus.CREATED);
				
	}
	
//	@GetMapping("/banks/{id}")
//	public ResponseEntity<List<BankAccountDTO>> getAccountByWalletIt(@PathVariable("id") Integer walletId)
//	{
//		return new ResponseEntity<>(bankService.getAccountByWalletId(walletId), HttpStatus.OK);
//	}
//	
//	@GetMapping("/banks/wallet/{walletId}")
//	public ResponseEntity<List<BankAccountDTO>> viewAllAccount(@PathVariable("walletId") Integer walletId)
//	{
//		return new ResponseEntity<List<BankAccountDTO>>(bankService.viewAllAccount(walletId),HttpStatus.ACCEPTED);
//	}
//   
	@DeleteMapping("/banks/{accountNumber}")
	public ResponseEntity<String> removeAccount(@RequestParam("key") String key,@PathVariable("accountNumber") Integer accountNumber)
	{
		UserSession user=userDao.findByUuid(key);
		if(user==null) {
			throw new CustomerNotFoundException("You are not authoraised person please login first.");
		}
		LocalDateTime prev=user.getDateTime();
		LocalDateTime date=LocalDateTime.now();
		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
			userDao.delete(user);
			throw new CustomerNotFoundException("Your session is expired please login again");
		}
		
		return new ResponseEntity<String>(bankService.removeAccount(accountNumber),HttpStatus.CREATED);
	}
	
}
