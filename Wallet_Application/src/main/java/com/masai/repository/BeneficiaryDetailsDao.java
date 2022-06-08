package com.masai.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.DTO.BeneficiaryDTO;
import com.masai.entity.BeneficiaryDetails;
import com.masai.entity.Wallet;
@Repository
public interface BeneficiaryDetailsDao extends JpaRepository<BeneficiaryDetails, Integer>{

	List<BeneficiaryDetails> findAllByWalletId(int a);
	BeneficiaryDetails findByMobileNo(String string);
	BeneficiaryDetails findByMobileNoAndWallet(String phone, Wallet wallet);
	
	public BeneficiaryDetails findByMobileNoAndWalletId(String mobile,Integer id);
	
	  @Query("select new com.masai.DTO.BeneficiaryDTO(b.id, b.mobileNo, b.name)"
		  		+ " from BeneficiaryDetails b where b.mobileNo=?1 AND b.wallet.id=?2")
	public BeneficiaryDTO findByWalletId(String mobile,Integer id);
}
