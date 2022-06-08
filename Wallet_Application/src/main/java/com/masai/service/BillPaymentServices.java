
package com.masai.service;

import java.util.List;

import com.masai.entity.BillPayment;


public interface BillPaymentServices {

	public BillPayment payBillPayment(BillPayment billPayment, String key);
	public List<BillPayment> viewBillPayment(String key);
	
}

