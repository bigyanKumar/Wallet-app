package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CostumerNotFoundException;
import com.masai.repository.BeneficiaryDetailsDao;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.userSessionDao.UserSessionDao;
import com.masai.repository.walletDao.WalletDaoJpa;
import com.masai.service.customerService.CustomerServiceImpl;

@Service
public class BeneficiaryDetailsService implements BeneficiaryDetailsServiceInter {
	@Autowired
	private BeneficiaryDetailsDao beneficiaryDao;
	@Autowired
	private CustomerDao customerDao;
	
	
	
	
	@Autowired
	private WalletDaoJpa wDao;
	@Autowired
	private UserSessionDao userDao;
	
	
	
	
	

	@Override
	public BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		customerDao.findById(beneficiaryDetail.getMobileNo()).orElseThrow(()->new CostumerNotFoundException("this mobile number not registered with any customer"));
		
		return beneficiaryDao.save(beneficiaryDetail);
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(String phone, Wallet wallet) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		//
		
		Optional<BeneficiaryDetails> optBene2=Optional.ofNullable(beneficiaryDao.findByMobileNoAndWallet(phone,wallet));
		
		BeneficiaryDetails bn;
		
		if(optBene2.isPresent()) {
			bn=optBene2.get();
			bn.setWallet(null);;
			beneficiaryDao.delete(bn);
			return bn;
		}
		
		else throw new CostumerNotFoundException("Invalid Input");
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber, Wallet wallet) throws CostumerNotFoundException {
		if(mobileNumber!=null&&mobileNumber!="") {
			return beneficiaryDao.findByMobileNoAndWallet(mobileNumber,wallet);
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

	@Override
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail, Wallet wallet)
			throws CostumerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
