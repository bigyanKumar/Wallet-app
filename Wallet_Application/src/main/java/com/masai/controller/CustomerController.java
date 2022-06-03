package com.masai.controller;

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
		return "Hello buddy";
	}
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer cs){
		
		return new ResponseEntity<>(csi.createAcc(cs),HttpStatus.CREATED);
	}
	
	@GetMapping("/customers/{mobile}")
	public ResponseEntity<Customer> showBlance(@PathVariable("mobile") String mobile){
		//System.out.println("1"+ mobile);
		
		return new ResponseEntity<>(csi.showBlacnce(mobile),HttpStatus.OK);
	}

}
