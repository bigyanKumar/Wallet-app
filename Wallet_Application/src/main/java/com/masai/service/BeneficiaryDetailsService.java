package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.BeneficiaryDetailsDao;
import com.masai.service.customerService.CustomerServiceImpl;

@Service
public class BeneficiaryDetailsService implements BeneficiaryDetailsServiceInter {
	@Autowired
	private BeneficiaryDetailsDao beneficiaryDao;
	@Autowired
	private CustomerServiceImpl customerService;

	@Override
	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		if(beneficiaryDetail!=null)
			return beneficiaryDao.save(beneficiaryDetail);
		else throw new CostumerNotFoundException("Invalid Input");
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		//
		beneficiaryDetail=beneficiaryDao.findById(beneficiaryDetail.getId()).orElseThrow(()-> new CostumerNotFoundException("Beneficiary nor found"));
		if(beneficiaryDetail!=null) {
			beneficiaryDetail.setWallet(null);
			beneficiaryDao.save(beneficiaryDetail);
			beneficiaryDao.delete(beneficiaryDetail);
			return beneficiaryDetail;
		}
			 
		else throw new CostumerNotFoundException("Invalid Input");
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CostumerNotFoundException {
		if(mobileNumber!=null&&mobileNumber!="") {
			return beneficiaryDao.findByMobileNo(mobileNumber);
		}
		throw new CostumerNotFoundException("Invalid Input");
		
	}

	@Override
	public List<BeneficiaryDetails> viewAllBeneficiary(Customer customer) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		if(customer!=null) {
			int a=customer.getWallet().getId();
			return beneficiaryDao.findAllByWalletId(a);
		}
		throw new CostumerNotFoundException("Invalid Input");
	}
	
	
}
