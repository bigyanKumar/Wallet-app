package com.masai.controller;

import java.time.LocalDateTime;
import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.masai.controller.dtos.BeneficiaryDetailsDto;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.UserSession;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BeneficiaryDetailsDao;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.userSessionDao.UserSessionDao;
import com.masai.repository.walletDao.WalletDaoJpa;
import com.masai.service.BeneficiaryDetailsService;


@RestController
public class BeneficiaryDetails2Controller {
	
	@Autowired
	private BeneficiaryDetailsService beneficiaryDetailsService;
	@Autowired
	private BeneficiaryDetailsDao beneficiaryDao;
	@Autowired
	private WalletDaoJpa wDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UserSessionDao userDao;

	@PostMapping(value="/Beneficiary0services/add")
	public BeneficiaryDetails addBeneficiaryRest(@RequestBody BeneficiaryDetails beneficiaryDetail, @RequestParam("key") String key) throws CustomerNotFoundException {
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
		beneficiaryDetail.setWallet(wallet);
		
		
		Optional<BeneficiaryDetails> optBene2=Optional.ofNullable(beneficiaryDao.findByMobileNoAndWallet(beneficiaryDetail.getMobileNo(),wallet));
		
		
		
		if(optBene2.isPresent()) {
			throw new CustomerNotFoundException("beneficiary already exist");
		}
		
//		wDao.findById(beneficiaryDto.walletId).orElseThrow(()-> new CostumerNotFoundException("wallet nor found"));
//		Optional<Wallet> wallet=Optional.ofNullable(wDao.getById(beneficiaryDto.walletId));
//		Wallet wallet2=wallet.get();
//		if(beneficiaryDto!=null&&wallet.isPresent())
//			
		return  beneficiaryDetailsService.addBeneficiary(beneficiaryDetail);
//		else throw new CostumerNotFoundException("Invalid Input");
	}

	
	
	
	
	
	
	
	
	
	@DeleteMapping(value="/Beneficiary0services")
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

	
	
	
	
	
	
	
	
	
	
	@GetMapping(value="/Beneficiary0services/view/")
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
			return beneficiaryDetailsService.viewBeneficiary(mobileNumber,wallet);
		}
		throw new CustomerNotFoundException("Invalid Input");
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@PostMapping(value="/Beneficiary0services/viewall")
		public List<BeneficiaryDetails> viewAllBeneficiaryRest(@RequestBody Customer customer,@RequestParam("key") String key) throws CustomerNotFoundException {
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
		
		
		
		
		System.out.println(customer.getWallet().getId());
		if(customer!=null) {
			int a=customer.getWallet().getId();
			return beneficiaryDetailsService.viewAllBeneficiary(customer);
		}
		throw new CustomerNotFoundException("Invalid Input");
	}
	
//asda
	
}
