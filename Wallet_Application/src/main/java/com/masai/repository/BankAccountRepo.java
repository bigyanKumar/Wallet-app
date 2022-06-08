package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.masai.DTO.BankAccountDTO;
import com.masai.entity.BankAccount;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{
	

        @Query("select new com.masai.DTO.BankAccountDTO(b.accountNo, b.bankName, b.ifscCode, b.bankBalance)"
  		+ " from BankAccount b where b.wallet.id=:val")
           public List<BankAccountDTO> viewAccount(@Param("val") Integer wallet);    

	public BankAccount findByBankNameAndWalletId(String bankName,Integer id);
	
	public List<BankAccount> findByWalletId(Integer walletId);
	
//	  @Query("select b.accountNo, b.bankName, b.ifscCode, b.bankBalance from BankAccount b where b.wallet.id=:val")
//  public List<String> viewAccount(@Param("val") Integer wallet);

	 
	
	

}

