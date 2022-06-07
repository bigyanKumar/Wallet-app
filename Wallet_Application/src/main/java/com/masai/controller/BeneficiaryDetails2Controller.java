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
import com.masai.entity.BenificiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.walletDao.WalletDaoJpa;
import com.masai.service.BeneficiaryDetailsService;

@RestController
public class BeneficiaryDetails2Controller{
	
	@Autowired
	private BeneficiaryDetailsService beneficiaryDetailsService;
	@Autowired
	private WalletDaoJpa wDao;

	@PostMapping(value="/Beneficiary0services/add")
	public BenificiaryDetails addBeneficiaryRest(@RequestBody BeneficiaryDetailsDto beneficiaryDto) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		wDao.findById(beneficiaryDto.walletId).orElseThrow(()-> new CostumerNotFoundException("wallet nor found"));
		Optional<Wallet> wallet=Optional.ofNullable(wDao.getById(beneficiaryDto.walletId));
		Wallet wallet2=wallet.get();
		if(beneficiaryDto!=null&&wallet.isPresent())
			return beneficiaryDetailsService.addBeneficiary(new BenificiaryDetails(0,beneficiaryDto.name,beneficiaryDto.mobileNo,wallet2));
		else throw new CostumerNotFoundException("Invalid Input");
	}

	@GetMapping(value="/Beneficiary0services/delete/{id}")
	public BenificiaryDetails deleteBeneficiaryRest(@PathVariable("id") Integer id) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		BenificiaryDetails beneficiaryDetail=new BenificiaryDetails();
		beneficiaryDetail.setId(id);
		if(beneficiaryDetail!=null) {
			
			return  beneficiaryDetailsService.deleteBeneficiary(beneficiaryDetail);
		}
			 
		else throw new CostumerNotFoundException("Invalid Input");
	}

	@GetMapping(value="/Beneficiary0services/view/{mobileNo}")
	public BenificiaryDetails viewBeneficiaryRest(String mobileNumber) throws CostumerNotFoundException {
		if(mobileNumber!=null&&mobileNumber!="") {
			return beneficiaryDetailsService.viewBeneficiary(mobileNumber);
		}
		throw new CostumerNotFoundException("Invalid Input");
		
	}

	@PostMapping(value="/Beneficiary0services/viewall")
		public List<BenificiaryDetails> viewAllBeneficiaryRest(@RequestBody Customer customer) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		System.out.println(customer.getWallet().getId());
		if(customer!=null) {
			int a=customer.getWallet().getId();
			return beneficiaryDetailsService.viewAllBeneficiary(customer);
		}
		throw new CostumerNotFoundException("Invalid Input");
	}
	
//asda
	
}
