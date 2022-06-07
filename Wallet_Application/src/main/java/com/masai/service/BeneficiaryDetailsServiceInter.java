package com.masai.service;

import java.util.List;

import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Customer;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CostumerNotFoundException;

public interface BeneficiaryDetailsServiceInter {
	BeneficiaryDetails addBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail) throws CostumerNotFoundException;
	BeneficiaryDetails viewBeneficiary(String mobileNumber) throws CostumerNotFoundException;
	List<BeneficiaryDetails> viewAllBeneficiary(Customer customer) throws CostumerNotFoundException;
	BeneficiaryDetails viewBeneficiary(String mobileNumber, Wallet wallet) throws CostumerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(BeneficiaryDetails beneficiaryDetail, Wallet wallet)
			throws CostumerNotFoundException;
	BeneficiaryDetails deleteBeneficiary(String phone, Wallet wallet) throws CostumerNotFoundException;
}

































