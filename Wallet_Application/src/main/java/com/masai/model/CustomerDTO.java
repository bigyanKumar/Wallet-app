package com.masai.model;

public class CustomerDTO {

	private long MobileNo;
	
	private String password;

	public CustomerDTO(long mobileNo, String password) {
		super();
		MobileNo = mobileNo;
		this.password = password;
	}
	public CustomerDTO(){
		
	}

	public long getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(long mobileNo) {
		MobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
