package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {
	
	@Id
	private Integer accountNo;
	private String ifscCode; 
	private String bankName;
	private double balance;
	private Integer walletId;
	public Integer getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Integer getWalletId() {
		return walletId;
	}
	public void setWalletId(Integer walletId) {
		this.walletId = walletId;
	}
	/**
	 * 
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(Integer accountNo, String ifscCode, String bankName, double balance, Integer walletId) {
		super();
		this.accountNo = accountNo;
		this.ifscCode = ifscCode;
		this.bankName = bankName;
		this.balance = balance;
		this.walletId = walletId;
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", ifscCode=" + ifscCode + ", bankName=" + bankName + ", balance="
				+ balance + ", walletId=" + walletId + "]";
	}
	
	
}
