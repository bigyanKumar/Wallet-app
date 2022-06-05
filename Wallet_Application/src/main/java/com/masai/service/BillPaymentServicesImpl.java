package com.masai.service;

import java.time.LocalDateTime;

//import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.BillPayment;
import com.masai.entity.Transaction;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.InsufficientAmountException;
import com.masai.repository.BillPaymentDAO;
import com.masai.repository.WalletDao;
	


@Service
public class BillPaymentServicesImpl implements BillPaymentServices {

	@Autowired
	private BillPaymentDAO billpd;
	
	@Autowired
	private WalletDao walletdao;
	
	@Autowired
	private TransactionDAO transactionDAO;

	@Override
	public String payBill(double amount, String billType) {
		
		if(wallet.getBalance() <= amount) {
			throw new InsufficientAmountException("Insufficient amount in wallet");
		}
		
		wallet.setWalletBalance(wallet.getWalletBalance() - amount);
		  Transaction tx = new Transaction();
		    tx.setAmount(amount);
		    tx.setDateTime(LocalDateTime.now());
		    tx.setDescription(billType);
		    tx.setTransactionType("Debit");
		    
		    
			transactionDAO.save(myTransaction);
			walletDAO.save(wallet);
			
		
		return "Successfully done...  "  ;
	}
	
	
	
	
	

//	@Override
//	public String electricityBillPayment(double amount) {
//		if(5000< amount) 
//			throw new InsufficientAmountException("Please Check Your wallet you wallet balanced less than your pay Amount. ");
//
//		return "successfully done..."+ (5000-amount);
//	}



//	@Override
//	public BillPayment saveBillpayment(BillPayment billPayment) {
//		billPayment.setAmount(5000);
//		
//		return billpd.save(billPayment);
//	}

	
	
	
}
