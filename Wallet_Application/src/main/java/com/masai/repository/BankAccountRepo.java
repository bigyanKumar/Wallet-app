package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.masai.entity.BankAccount;

@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, Integer>{
	
//	
//   @Query("select new com.masai.entity.BankAccountDTO(b.accountNo, b.bankName, b.ifscCode, b.bankBalance) from BankAccount b where b.walletId=:val")
//       public List<BankAccountDTO> viewAccount(@Param("val") Integer walletId);
//	 
	// @Query("select b.accountNo, b.bankName, b.ifscCode, b.bankBalance from BankAccount b where b.wellet_id= ?1")
	 // public List<BankAccountDTO> viewAllAccount(Integer walletId);
	
	 
	public BankAccount findByBankNameAndWalletId(String bankName,Integer id);
	
	public List<BankAccount> findByWalletId(Integer walletId);
	 
	
	

}
