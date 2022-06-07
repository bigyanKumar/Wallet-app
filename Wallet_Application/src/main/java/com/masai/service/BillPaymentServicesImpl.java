package com.masai.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

//import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.BillPayment;
import com.masai.entity.Customer;
import com.masai.entity.Transaction;
import com.masai.entity.Wallet;
import com.masai.globalExceptionHandler.CustomerNotFoundException;
import com.masai.globalExceptionHandler.InsufficientAmountException;
import com.masai.repository.BillPaymentDao.BillPaymentDao;
import com.masai.repository.customerDao.CustomerDao;
import com.masai.repository.transactionDao.TransactionDao;
import com.masai.repository.userSessionDao.UserSessionDao;
import com.masai.repository.walletDao.WalletDaoJpa;

	


@Service
public class BillPaymentServicesImpl implements BillPaymentServices {

	@Autowired
	private BillPaymentDao billpd;
	
	@Autowired
	private WalletDaoJpa walletdao;
	
	@Autowired
	private TransactionDao transactionDAO;
	
	@Autowired
	private UserSessionDao userDao;

	
    @Autowired
    private CustomerDao customer;
    
    
    @Autowired
    private UserSessionDao user;
    
    
//	PayBill Implementation
	
	@Override
	public BillPayment payBillPayment(BillPayment billPayment, String key) {
		
		
		if(billPayment.getWallet().getBalance() <= billPayment.getAmount()) {
			throw new InsufficientAmountException("Insufficient amount in wallet");
		}
		
		billPayment.getWallet().setBalance(billPayment.getWallet().getBalance()-billPayment.getAmount());
		
		billPayment.setPaymentDate(LocalDateTime.now());
		Transaction myTransaction = new Transaction();
		myTransaction.setAmount(billPayment.getAmount());
		myTransaction.setDateTime(LocalDateTime.now());
		myTransaction.setDescription(billPayment.getBillType());
		myTransaction.setTransactionType("Debit");
		
		billPayment.getWallet().getTran().add(myTransaction);
		billpd.save(billPayment);
		

		return billPayment;
	}



	@Override
	public List<BillPayment> viewBillPayment(String key) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
        Optional<Customer> cust=customer.findById(user.findByUuid(key).getMobile());
        List<BillPayment> bill = billpd.findAllBillPaymentsByWalletId(cust.get().getWallet().getId());
        
        return bill;
	}





	
	
	

	
	
	

	

	
	
	
}
