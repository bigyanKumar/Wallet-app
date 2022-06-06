package com.masai.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.controller.dtos.BeneficiaryDetailsDto;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.walletDao.WalletDaoJpa;
import com.masai.service.BeneficiaryDetailsService;


@RestController
public class BeneficiaryDetails2Controller{
	
	@Autowired
	private BeneficiaryDetailsService beneficiaryDetailsService;
	@Autowired
	private WalletDaoJpa wDao;

	@PostMapping(value="/Beneficiary0services/add")
	public BeneficiaryDetails addBeneficiaryRest(@RequestBody BeneficiaryDetailsDto beneficiaryDto) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		wDao.findById(beneficiaryDto.walletId).orElseThrow(()-> new CustomerNotFoundException("wallet nor found"));
		Optional<Wallet> wallet=Optional.ofNullable(wDao.getById(beneficiaryDto.walletId));
		Wallet wallet2=wallet.get();
		if(beneficiaryDto!=null&&wallet.isPresent())
			return beneficiaryDetailsService.addBeneficiary(new BeneficiaryDetails(0,beneficiaryDto.name,beneficiaryDto.mobileNo,wallet2));
		else throw new CustomerNotFoundException("Invalid Input");
	}

	@GetMapping(value="/Beneficiary0services/delete/{id}")
	public BeneficiaryDetails deleteBeneficiaryRest(@PathVariable("id") Integer id) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		BeneficiaryDetails beneficiaryDetail=new BeneficiaryDetails();
		beneficiaryDetail.setId(id);
		if(beneficiaryDetail!=null) {
			
			return  beneficiaryDetailsService.deleteBeneficiary(beneficiaryDetail);
		}
			 
		else throw new CustomerNotFoundException("Invalid Input");
	}

	@GetMapping(value="/Beneficiary0services/view/{mobileNo}")
	public BeneficiaryDetails viewBeneficiaryRest(String mobileNumber) throws CustomerNotFoundException {
		if(mobileNumber!=null&&mobileNumber!="") {
			return beneficiaryDetailsService.viewBeneficiary(mobileNumber);
		}
		throw new CustomerNotFoundException("Invalid Input");
		
	}

	@PostMapping(value="/Beneficiary0services/viewall")
		public List<BeneficiaryDetails> viewAllBeneficiaryRest(@RequestBody Customer customer) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(customer.getWallet().getId());
		if(customer!=null) {
			int a=customer.getWallet().getId();
			return beneficiaryDetailsService.viewAllBeneficiary(customer);
		}
		throw new CustomerNotFoundException("Invalid Input");
	}
	
//asda
	
}
