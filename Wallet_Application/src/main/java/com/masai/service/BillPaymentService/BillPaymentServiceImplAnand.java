//package com.masai.service.BillPaymentService;
//package com.masai.service.BillPaymentService;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.masai.entity.BillPayment;
//import com.masai.entity.Transaction;
//import com.masai.entity.Wallet;
//import com.masai.globalExceptionHandler.CostumerNotFoundException;
//import com.masai.repository.BillPaymentDao.BillPaymentDao;
//import com.masai.repository.walletDao.WalletDaoJpa;
//
//@Service
//public class BillPaymentServiceImpl implements BillPaymentService {
//
////	@Autowired
////	private BillPaymentDao bpDao;
//	
//	@Autowired
//	private WalletDaoJpa wDao; 
//	
//	@Override
//	public BillPayment addBillPayment(BillPayment billPayment) {
//            
////		   System.out.println(billPayment);
////		   
////		   return bpDao.save(billPayment);
//		
//		    Optional<Wallet> wallet = wDao.findById(billPayment.getWallet());
//		    
//		    if(wallet.isPresent())
//		    {
//		    	Wallet w =wallet.get();
//		    w.setBalance(w.getBalance()-billPayment.getAmount());
//		    Transaction tx = new Transaction();
//		    tx.setAmount(billPayment.getAmount());
//		    tx.setDateTime(LocalDateTime.now());
//		    tx.setDescription(billPayment.getBillType());
//		    tx.setTransactionType("Debit");
//		    w.getTran().add(tx);
//		     wDao.save(w);
//		    return bpDao.save(billPayment);
//		    }
//		    else
//		    	throw new CostumerNotFoundException("Wallet Not Found....");
//		   
//	}
//
//}
