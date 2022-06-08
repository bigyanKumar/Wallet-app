package com.masai.service;

import java.util.List;

import com.masai.DTO.BeneficiaryDTO;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface BeneficiaryDetailsServiceInter {
	BeneficiaryDTO addBeneficiary(Customer cust,Wallet wallet) throws CustomerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CustomerNotFoundException;
	BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CustomerNotFoundException;
	List<BeneficiaryDetails> viewAllBeneficiary(Customer customer) throws CustomerNotFoundException;
	BeneficiaryDetails viewBeneficiary(String mobileNumber, Wallet wallet) throws CustomerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail, Wallet wallet)
			throws CustomerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(String phone, Wallet wallet) throws CustomerNotFoundException;
}

































