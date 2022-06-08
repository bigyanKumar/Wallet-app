package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.DTO.BeneficiaryDTO;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.repository.BeneficiaryDetailsDao;



@Service
public class BeneficiaryDetailsService implements BeneficiaryDetailsServiceInter {
	@Autowired
	private BeneficiaryDetailsDao beneficiaryDao;
//	@Autowired
//	private CustomerDao customerDao;
//	
//	
//	
//	
//	@Autowired
//	private WalletDaoJpa wDao;
//	@Autowired
//	private UserSessionDao userDao;
//	
	
	
	
	

	@Override
	public BeneficiaryDTO addBeneficiary(Customer cust,Wallet wallet) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		BeneficiaryDetails bene=new BeneficiaryDetails();
		
		bene.setMobileNo(cust.getMobileNumber());
		bene.setName(cust.getName());
		bene.setWallet(wallet);
		beneficiaryDao.save(bene);
		
		return beneficiaryDao.findByWalletId(wallet.getId());
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(String phone, Wallet wallet) throws CustomerNotFoundException {
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
		
		else throw new CustomerNotFoundException("Invalid Input");
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber, Wallet wallet) throws CustomerNotFoundException {
		if(mobileNumber!=null&&mobileNumber!="") {
			return beneficiaryDao.findByMobileNoAndWallet(mobileNumber,wallet);
		}
		throw new CustomerNotFoundException("Invalid Input");
		
	}

	@Override
	public List<BeneficiaryDetails> viewAllBeneficiary(Customer customer) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		if(customer!=null) {
			int a=customer.getWallet().getId();
			return beneficiaryDao.findAllByWalletId(a);
		}
		throw new CustomerNotFoundException("Invalid Input");
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail, Wallet wallet)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
