package com.masai.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long customerId;
	
	private String name;
	
	private long mobileNo;
	
	private String password;
	
	private String email;

	public Customer(long customerId, String name, long mobileNo, String password, String email) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.password = password;
		this.email = email;
	}
	public Customer() {
		
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	
}
