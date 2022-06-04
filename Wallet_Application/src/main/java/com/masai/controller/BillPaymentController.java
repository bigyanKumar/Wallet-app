package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
//	
//	
	@GetMapping("/electricBill/{a}/{b}")
	public String saveBillPayment( @PathVariable("a")  Double amount, @PathVariable("b") String biilType){
		
		return billservices.electricityBillPayment(amount , biilType);
//		return null;
	}
	
}
