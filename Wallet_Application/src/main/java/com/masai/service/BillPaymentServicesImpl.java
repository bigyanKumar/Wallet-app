package com.masai.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.entity.BillPayment;
import com.masai.globalExceptionHandler.InsufficientAmountException;
import com.masai.repository.BillPaymentDAO;
	


@Service
public class BillPaymentServicesImpl implements BillPaymentServices {

	@Autowired
	private BillPaymentDAO billpd;

	
	@Override
	public String electricityBillPayment(Double amount, String billType) {
			
		if(5000< amount) {
			throw new InsufficientAmountException("Please Check Your wallet you wallet balanced less than your pay Amount. ");
			
		}
		BillPayment bp= BillPayment.BillPaymentFactory("ElectricBill", 100, LocalDateTime.now(), 1);
		billpd.save(bp);
		
		
		return "successfully done... "+ billType;
	}

	public BillPaymentServicesImpl(BillPaymentDAO billpd) {
		super();
		this.billpd = billpd;
	}


	public BillPaymentServicesImpl() {
		super();
	}




	public BillPaymentDAO getBillpd() {
		return billpd;
	}




	public void setBillpd(BillPaymentDAO billpd) {
		this.billpd = billpd;
	}
	
	
	
	
}
