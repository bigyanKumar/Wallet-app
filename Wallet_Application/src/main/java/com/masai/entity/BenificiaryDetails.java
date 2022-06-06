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
public class BenificiaryDetails {
	
	@Id
	private String mobileNo;
	private String name;
	@ManyToOne(targetEntity = Wallet.class,cascade = CascadeType.ALL)
	@JoinColumn(name="walletId")
	private Wallet wallet;
}
