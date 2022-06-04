//package com.masai.controller;

////import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.masai.entity.CustomerDTO;
//import com.masai.service.customerService.CustomerSessionImpl;
//
//@RestController
//public class LoginController {
//	
//	@Autowired
//	CustomerSessionImpl cusService;
//	
//	@PostMapping("/login")
//	public ResponseEntity<String> loginToAccount(@RequestBody CustomerDTO cusDto)
//	{
//		return new ResponseEntity<String>(cusService.LoginToAccount(cusDto),HttpStatus.CREATED);
//	}
//	
//}
