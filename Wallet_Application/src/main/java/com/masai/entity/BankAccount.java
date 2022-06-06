package com.masai.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankAccount {
	
	@Id
	private Integer id;
	private Integer accoutNo;
	private String ifscCode;
	private String Bankname;
	private Double balance;
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = Wallet.class)
	@JoinColumn(name="walletId")
	private Wallet wallet;

}
