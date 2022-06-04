package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.BillPayment;
import com.masai.service.BillPaymentService.BillPaymentService;

@RestController
public class BillPaymentController {
	
     @Autowired
     BillPaymentService bpService;
     
     @PostMapping("/bills")
     public ResponseEntity<BillPayment> createBill(@RequestBody BillPayment billPayment)
     {
    	 return new ResponseEntity<BillPayment>(bpService.addBillPayment(billPayment),HttpStatus.CREATED);
     }

}
