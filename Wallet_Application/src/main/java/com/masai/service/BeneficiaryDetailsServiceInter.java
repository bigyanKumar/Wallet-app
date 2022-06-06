package com.masai.service;

import java.util.List;

import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CustomerNotFoundException;

public interface BeneficiaryDetailsServiceInter {
	BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CustomerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CustomerNotFoundException;
	BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CustomerNotFoundException;
	List<BeneficiaryDetails> viewAllBeneficiary(Customer customer) throws CustomerNotFoundException;
}

































