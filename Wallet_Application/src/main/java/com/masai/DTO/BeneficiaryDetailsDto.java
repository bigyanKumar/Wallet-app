package com.masai.DTO;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.masai.entity.Wallet;

public class BeneficiaryDetailsDto {
	public Integer id;
	public String name;
	public String mobileNo;
	public Integer walletId;
}
