//package com.masai.service.BillPaymentService;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.masai.entity.BillPayment;
//import com.masai.globalExceptionHandler.InsufficientAmountException;
//import com.masai.repository.BillPaymentDAO;
//import com.masai.service.BillPaymentServices;
//	
//
//
//@Service
//public class BillPaymentServicesImpl implements BillPaymentServices {
//
//	@Autowired
//	private BillPaymentDAO billpd;
//
//	@Override
//	public String electricityBillPayment(double amount) {
//		if(5000< amount) 
//			throw new InsufficientAmountException("Please Check Your wallet you wallet balanced less than your pay Amount. ");
//			
//			
//		return "successfully done..."+ (5000-amount);
//	}
//
//	
//
//	
//	
//
////	@Override
////	public BillPayment saveBillpayment(BillPayment billPayment) {
////		billPayment.setAmount(5000);
////		
////		return billpd.save(billPayment);
////	}
//
//	
//	
//	
//}
