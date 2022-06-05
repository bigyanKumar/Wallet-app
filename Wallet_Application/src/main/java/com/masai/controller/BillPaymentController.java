package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.BillPayment;
import com.masai.service.BillPaymentServices;
import com.masai.service.BillPaymentServicesImpl;



@RestController
public class BillPaymentController {
	
	@Autowired
	private BillPaymentServices billservices;
	
	
	@GetMapping("/payBill/{a}/{b}")
	public String saveBillPayment(@PathVariable("a") double amount, @PathVariable("b") String billType){
		
		
		
		
		return billservices.payBill(amount, billType);

	}

	
	
	
//	@PostMapping("/billpayment")
//	public ResponseEntity<BillPayment> saveBillPaymentHand(@RequestBody BillPayment billPayment){
//		
//	 BillPayment saveBill= 	billservices.saveBillpayment(billPayment);
//	 
//	 return new ResponseEntity<BillPayment>(saveBill, HttpStatus.CREATED);
//	}
//	
}
