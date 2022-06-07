package com.masai.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.BillPayment;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.userSessionDao.UserSessionDao;
import com.masai.service.BillPaymentServices;
import com.masai.service.BillPaymentServicesImpl;



@RestController
public class BillPaymentController {
	
	@Autowired
	private BillPaymentServices billservices;
	
	@Autowired
	private UserSessionDao userDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@PostMapping("/electricity")
	public ResponseEntity<BillPayment> payBill(@Valid @RequestBody BillPayment billPayment, @RequestParam("key") String key) {
		
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
		Optional<Customer> cs = customerDao.findById(user.getMobile());
		billPayment.setWallet(cs.get().getWallet());
		return new ResponseEntity<BillPayment>( billservices.payBillPayment(billPayment,key),HttpStatus.ACCEPTED);
		
	}
	

		@GetMapping("/bills")
		public List<BillPayment> getAllBillPayment(@RequestParam("key") String key){
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
			
			return billservices.viewBillPayment(key);
		}

	

}
