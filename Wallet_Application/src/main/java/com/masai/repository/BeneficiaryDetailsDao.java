package com.masai.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Wallet;
@Repository
public interface BeneficiaryDetailsDao extends JpaRepository<BeneficiaryDetails, Integer>{

	List<BeneficiaryDetails> findAllByWalletId(int a);
	BeneficiaryDetails findByMobileNo(String string);
	BeneficiaryDetails findByMobileNoAndWallet(String phone, Wallet wallet);
	
}
