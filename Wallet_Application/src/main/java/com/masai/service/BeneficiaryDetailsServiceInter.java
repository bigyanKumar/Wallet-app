package com.masai.service;

import java.util.List;

import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

public interface BeneficiaryDetailsServiceInter {
	BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException;
	BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CostumerNotFoundException;
	List<BeneficiaryDetails> viewAllBeneficiary(Customer customer) throws CostumerNotFoundException;
}

































