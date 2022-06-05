
package com.masai.service;

import com.masai.entity.BillPayment;

public interface BillPaymentServices {

//	public String electricityBillPayment(double amount);
	public String payBill(double amount, String billType);
	
//	public BillPayment saveBillpayment(BillPayment billPayment);
}

