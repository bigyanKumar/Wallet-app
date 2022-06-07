package com.masai.service;

import java.util.List;


import com.masai.entity.BenificiaryDetails;
import com.masai.entity.Customer;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

public interface BeneficiaryDetailsServiceInter {
	BenificiaryDetails addBeneficiary(BenificiaryDetails beneficiaryDetail) throws CostumerNotFoundException;
	BenificiaryDetails deleteBeneficiary(BenificiaryDetails beneficiaryDetail) throws CostumerNotFoundException;
	BenificiaryDetails viewBeneficiary(String mobileNumber) throws CostumerNotFoundException;
	List<BenificiaryDetails> viewAllBeneficiary(Customer customer) throws CostumerNotFoundException;
}

































