package com.masai.entity;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BillPayment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer billId;
	private String billType;
	private Double amount;
	private LocalDateTime paymentDate;
	@ManyToOne(targetEntity = Wallet.class,cascade =CascadeType.ALL)
	@JoinColumn(name="walletId")
	private Wallet wallet;
}
