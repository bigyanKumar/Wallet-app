package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.BeneficiaryDetails;

public interface BeneficiaryDetailsDao extends JpaRepository<BeneficiaryDetails, Integer>{

	List<BeneficiaryDetails> findAllByWalletId(int a);

	BeneficiaryDetails findByMobileNo(String string);
	
}
