//package com.masai.controller;
//
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.masai.DTO.CustomerDTO;
//import com.masai.entity.Customer;
//import com.masai.entity.UserSession;
//import com.masai.globalExceptionHandler.CostumerNotFoundException;
//import com.masai.repository.userSessionDao.UserSessionDao;
//import com.masai.service.customerService.CustomerServiceImpl;
//import com.masai.service.loginService.LoginServiceImpl;
//
//@RestController
//public class CustomerController {
//	
//	@Autowired
//	private CustomerServiceImpl csi;
//	@Autowired
//	private LoginServiceImpl login;
//	@Autowired
//	private UserSessionDao userDao;
//	
//	@GetMapping("/get")
//	public String getDeta() {
//		return "Hello buddy this is testing";
//	}
//	@PostMapping("userlogin")
//	public ResponseEntity<UserSession> loginSession(@RequestBody CustomerDTO cusD){
//		
//		return new ResponseEntity<>(login.loginService(cusD),HttpStatus.OK);
//	}
//	@GetMapping("userlogout")
//	public ResponseEntity<String> logoutSession(@RequestParam("key") String key){
//		return new ResponseEntity<>(login.logoutService(key),HttpStatus.OK);
//	}
//	
//	@PostMapping("/customers")
//	public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer cs){
//		return new ResponseEntity<>(csi.createAcc(cs),HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/customers/{mobile}")
//	public ResponseEntity<Customer> showBlance(@PathVariable("mobile") String mobile, @RequestParam("key") String key){
//		UserSession user=userDao.findByUuid(key);
//		if(user==null) {
//			throw new CostumerNotFoundException("You are not authoraised person please login first.");
//		}
//		LocalDateTime prev=user.getDateTime();
//		LocalDateTime date=LocalDateTime.now();
//		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
//			userDao.delete(user);
//			throw new CostumerNotFoundException("Your session is expired please login again");
//		}
//		Customer ucs = csi.showBlacnce(mobile);
//		ucs.getWallet().setTran(null);
//		return new ResponseEntity<>(ucs, HttpStatus.OK);
//	}
//	@PostMapping("customers/{mobile}/{amount}")
//	public ResponseEntity<Customer> depositAmt(@PathVariable("mobile") String mobile, @PathVariable("amount") Double amount,@RequestParam("key") String key){
//		UserSession user=userDao.findByUuid(key);
//		if(user==null) {
//			throw new CostumerNotFoundException("You are not authoraised person please login first.");
//		}
//		LocalDateTime prev=user.getDateTime();
//		LocalDateTime date=LocalDateTime.now();
//		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
//			userDao.delete(user);
//			throw new CostumerNotFoundException("Your session is expired please login again");
//		}
//		return new ResponseEntity<>(csi.depositAmount(mobile, amount),HttpStatus.OK);
//	}
//	
//	@GetMapping("customers")
//	public ResponseEntity<List<Customer>> getCustomer(@RequestParam("key") String key){
//		UserSession user=userDao.findByUuid(key);
//		if(user==null) {
//			throw new CostumerNotFoundException("You are not authoraised person please login first.");
//		}
//		LocalDateTime prev=user.getDateTime();
//		LocalDateTime date=LocalDateTime.now();
//		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
//			userDao.delete(user);
//			throw new CostumerNotFoundException("Your session is expired please login again");
//		}
//		List<Customer> l1c=csi.getListCustomer();
//		l1c.forEach((cs)-> cs.setWallet(null));
//		return new ResponseEntity<>(l1c,HttpStatus.OK);
//	}
//	@PatchMapping("/customers")
//	public ResponseEntity<Customer> updateAccount(@Valid @RequestBody Customer cs,@RequestParam("key") String key){
//		UserSession user=userDao.findByUuid(key);
//		if(user==null) {
//			throw new CostumerNotFoundException("You are not authoraised person please login first.");
//		}
//		LocalDateTime prev=user.getDateTime();
//		LocalDateTime date=LocalDateTime.now();
//		if (prev.getDayOfMonth() != date.getDayOfMonth()) {
//			userDao.delete(user);
//			throw new CostumerNotFoundException("Your session is expired please login again");
//		}
//		
//		Customer ucs=csi.updateCustomer(cs);
//		ucs.setWallet(null);
//		return new ResponseEntity<>(ucs,HttpStatus.ACCEPTED);
//	}
//	
//
//}
