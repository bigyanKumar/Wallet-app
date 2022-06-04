package com.masai.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masai.entity.BillPayment;

public interface BillPaymentDAO extends JpaRepository<BillPayment, Integer> {
	
	@Query("select new com.masai.entity.BillPayment(s.billType,s.amount,s.paymentDateTime,s.walletId) from BillPayment s where s.walletId=:walletId")        
	public List<BillPayment> findAllBillPaymentWithwalletId(Integer walletId);
	
	
	
//	
//	private String billType;,
//	private double amount;
//	private LocalDateTime paymentDateTime;
//	private Integer walletId;
}
