package com.masai.controller;

import java.time.LocalDateTime;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.DTO.BeneficiaryDTO;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BeneficiaryDetailsDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.UserSessionDao;
import com.masai.service.BeneficiaryDetailsService;


@RestController
public class BeneficiaryDetails2Controller {
	
	@Autowired
	private BeneficiaryDetailsService beneficiaryDetailsService;
	@Autowired
	private BeneficiaryDetailsDao beneficiaryDao;
	@Autowired
//	private WalletDaoJpa wDao;
//	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UserSessionDao userDao;

	@PostMapping(value="/beneficiaryservices/add")
	public BeneficiaryDTO addBeneficiaryRest(@RequestParam String mobile, @RequestParam("key") String key) throws CustomerNotFoundException {
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
		
		Optional<Customer> custo=customerDao.findById(user.getMobile());
		Optional<Customer> custo2=customerDao.findById(mobile);
		if(!custo2.isPresent())
			throw new CustomerNotFoundException("This Customer detail not found in our database");
		if(custo.get().getMobileNumber()==custo2.get().getMobileNumber())
			throw new CustomerNotFoundException("You cann't insert same mobile number in beneficiary details");
	
		if(beneficiaryDao.findByMobileNoAndWalletId(mobile,custo.get().getWallet().getId())!=null)
			throw new CustomerNotFoundException("This beneficiary already exits to your account");
		
		return  beneficiaryDetailsService.addBeneficiary(custo2.get(),custo.get().getWallet());
	}

	
	
	
	
	
	
	
	
	
	@DeleteMapping(value="/beneficiaryservices/delete")
	public BeneficiaryDetails deleteBeneficiaryRest(@RequestParam("phone") String phone, @RequestParam("key") String key) throws CustomerNotFoundException {
		
		
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
		
		Optional<Customer> cusopt=customerDao.findById(user.getMobile());
//		Wallet wallet=cusopt.get().getWallet();
//		Optional<BeneficiaryDetails> optBene2=Optional.ofNullable(beneficiaryDao.findByMobileNoAndWallet(phone,wallet));
		
		return  beneficiaryDetailsService.deleteBeneficiary(phone,cusopt.get().getWallet());
		
		
		
//		BeneficiaryDetails beneficiaryDetail=new BeneficiaryDetails();
//		beneficiaryDetail.setId(id);
//		if(beneficiaryDetail!=null) {
//			
//			return  beneficiaryDetailsService.deleteBeneficiary(beneficiaryDetail);
//		}
//			 
//		else 
	}

	
	
	
	
	
	
	
	
	
	
	@GetMapping(value="/beneficiaryservices/view/")
	public BeneficiaryDetails viewBeneficiaryRest(@RequestParam String mobileNumber, @RequestParam("key") String key) throws CustomerNotFoundException {
		
		
		
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
		
		Optional<Customer> cusopt=customerDao.findById(user.getMobile());
		Wallet wallet=cusopt.get().getWallet();
		if(mobileNumber!=null&&mobileNumber!="") {
			 beneficiaryDetailsService.viewBeneficiary(mobileNumber,wallet).setWallet(null);
		}
		throw new CustomerNotFoundException("Invalid Input");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@PostMapping(value="/Beneficiaryservices/viewall")
		public List<BeneficiaryDetails> viewAllBeneficiaryRest(@RequestParam("key") String key) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		
		
		
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

		
		Optional<Customer> cusopt=customerDao.findById(user.getMobile());
		Customer customer=cusopt.get();
		System.out.println(customer.getWallet().getId());

			return beneficiaryDetailsService.viewAllBeneficiary(customer);
		
	}
	
}
