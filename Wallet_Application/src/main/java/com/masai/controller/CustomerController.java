package com.masai.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.entity.Customer;
import com.masai.service.customerService.CustomerServiceImpl;

@RestController
public class CustomerController {
	@Autowired
	private CustomerServiceImpl csi;
	@GetMapping("/get")
	public String getDeta() {
		return "Hello buddy this is testing";
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer cs){
		return new ResponseEntity<>(csi.createAcc(cs),HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/{mobile}")
	public ResponseEntity<Customer> showBlance(@PathVariable("mobile") String mobile){
		//System.out.println("1"+ mobile);
		Customer ucs=csi.showBlacnce(mobile);
		ucs.getWallet().setTran(null);
		return new ResponseEntity<>(ucs,HttpStatus.OK);
	}
	@PostMapping("customers/{mobile}/{amount}")
	public ResponseEntity<Customer> depositAmt(@PathVariable("mobile") String mobile, @PathVariable("amount") Double amount){
		return new ResponseEntity<>(csi.depositAmount(mobile, amount),HttpStatus.OK);
	}
	
	@GetMapping("customers")
	public ResponseEntity<List<Customer>> getCustomer(){
		List<Customer> l1c=csi.getListCustomer();
		l1c.forEach((cs)-> cs.setWallet(null));
		return new ResponseEntity<>(l1c,HttpStatus.OK);
	}
	@PostMapping("/updateCustomers")
	public ResponseEntity<Customer> updateAccount(@Valid @RequestBody Customer cs){
		
		Customer ucs=csi.updateCustomer(cs);
		ucs.setWallet(null);
		return new ResponseEntity<>(ucs,HttpStatus.ACCEPTED);
	}
	

}
