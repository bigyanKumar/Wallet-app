package com.masai.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.masai.DTO.BankAccountDTO;
import com.masai.entity.BankAccount;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BankAccountRepo;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;
import com.masai.service.BankAccountServiceImpl;

@RestController
public class BankAccountController{
	
	@Autowired
	BankAccountServiceImpl bankService;
	
	@Autowired
	UserSessionDao userDao;
	
	@Autowired
	BankAccountRepo bankDao;
	
	@Autowired
	CustomerDao cusDao;
	
	@PostMapping("/banks")
	public ResponseEntity<String> createNewAccount( @RequestParam("key") String key,@Valid @RequestBody BankAccount bankAccount)
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
		
		Optional<Customer> opt1 = cusDao.findById(user.getMobile());
		Optional<BankAccount> opt2 = bankDao.findById(accountNumber);
		if(opt1.get().getWallet().getId()!=opt2.get().getWallet().getId())
			 throw new CustomerNotFoundException("Failed: Invalid Uuui Id");
		
		
		
		return new ResponseEntity<BankAccount>(bankService.getAccountByAccountNumber(accountNumber),HttpStatus.CREATED);
				
	}
	
	@GetMapping("/findbanks")
	public ResponseEntity<List<BankAccountDTO>> getAccountByWalletIt(@RequestParam("key") String key)
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
		
		return new ResponseEntity<>(bankService.getAccountByWalletId(opt.get().getWallet().getId()), HttpStatus.OK);
	}
	
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
	
	
	@PostMapping("/transfermoney")
	 public ResponseEntity<BankAccount> sendMoney(@RequestParam("key") String key,@RequestParam("From A/c")Integer acc1,@RequestParam("To A/c") Integer acc2, @RequestParam("balance") double balance)
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
		
		
		
		Optional<Customer> opt1 = cusDao.findById(user.getMobile());
		Optional<BankAccount> opt2 = bankDao.findById(acc1);
		
		
		if(opt1.get().getWallet().getId()!=opt2.get().getWallet().getId())
			 throw new CustomerNotFoundException("Please Enter Correct Sending Account Id");
		
		
		return new ResponseEntity<BankAccount>(bankService.moneyTransfer(acc1, acc2, balance,opt1.get().getWallet()),HttpStatus.ACCEPTED);
	 }
	
	
	
}
