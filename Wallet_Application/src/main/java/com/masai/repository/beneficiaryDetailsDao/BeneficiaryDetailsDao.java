package com.masai.repository.beneficiaryDetailsDao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.BenificiaryDetails;

public interface BeneficiaryDetailsDao extends JpaRepository<BenificiaryDetails, String>{
	List<BenificiaryDetails> findAllByWalletId(int a);

	BenificiaryDetails findByMobileNo(String string);
	
}
