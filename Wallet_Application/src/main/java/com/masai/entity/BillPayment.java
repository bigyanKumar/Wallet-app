package com.masai.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

@AllArgsConstructor
public class BillPayment {

	@Id
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	private String billType;
	private Double amount;
	private String paymentDate;
	private Integer wallet;
    
		

=======
		private Integer billId;
	
		private String billType;
		private double amount;
		private LocalDateTime paymentDateTime;
		private Integer walletId;

		
		public static BillPayment BillPaymentFactory(String billType, double amount, LocalDateTime paymentDateTime, Integer walletId) {
			BillPayment bp  = new BillPayment(2,billType, amount,paymentDateTime,walletId );
			bp.billType = billType;
			bp.amount = amount;
			bp.paymentDateTime = paymentDateTime;
			bp.walletId = walletId;
			return bp;
			
		}
		
		public BillPayment(String billType, double amount, LocalDateTime paymentDateTime, Integer walletId) {
			super();
			this.billType = billType;
			this.amount = amount;
			this.paymentDateTime = paymentDateTime;
			this.walletId = walletId;
		}
>>>>>>> parent of 78eea8f (Merge branch 'main' of https://github.com/bigyanKumar/Wallet-app)

		public BillPayment() {
			super();
		}
}
