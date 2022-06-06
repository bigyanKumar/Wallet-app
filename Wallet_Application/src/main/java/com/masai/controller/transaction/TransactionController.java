package com.masai.controller.transaction;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.Date;
import com.masai.entity.Transaction;
import com.masai.entity.UserSession;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.userSessionDao.UserSessionDao;
import com.masai.service.transactionService.TransactionServiceImpl;

@RestController
public class TransactionController {
	
	@Autowired
	private UserSessionDao userDao;
	
	@Autowired
	private TransactionServiceImpl trans;
	
	@GetMapping("transactions")
	public ResponseEntity<List<Transaction>> viewTransaction(@RequestParam("key") String key){
		UserSession user=userDao.findByUuid(key);
		if(user==null) {
			throw new CostumerNotFoundException("You are not authoraised person please login first.");
		}
		LocalDateTime prev=user.getDateTime();
		LocalDateTime date=LocalDateTime.now();
		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
			userDao.delete(user);
			throw new CostumerNotFoundException("Your session is expired please login again");
		}
		
		return new ResponseEntity<>(trans.getTransaction(key),HttpStatus.OK);
	}
	
	@PostMapping("transactions")
	public ResponseEntity<List<Transaction>> viewTransactionByDate(@RequestParam("key") String key, @RequestBody Date datewise){
		UserSession user=userDao.findByUuid(key);
		if(user==null) {
			throw new CostumerNotFoundException("You are not authoraised person please login first.");
		}
		LocalDateTime prev=user.getDateTime();
		LocalDateTime date=LocalDateTime.now();
		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
			userDao.delete(user);
			throw new CostumerNotFoundException("Your session is expired please login again");
		}
		
		return new ResponseEntity<>(trans.getTransactionByDate(datewise, key),HttpStatus.OK);
	}
//	@PostMapping("transaction")
//	public ResponseEntity<List<Transaction>> viewTransactionByDate(@RequestParam("key") String key, @RequestBody Wallet wallet){
//		
//		return null;
//	}
	

}
