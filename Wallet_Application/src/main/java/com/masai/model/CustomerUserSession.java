package com.masai.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CustomerUserSession {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long id;
	
	@Column(unique=true)
	private long customerId;
	
	private String uuid;
	
	private  LocalDateTime localdatetime;
	
	public CustomerUserSession( long customerId, String uuid, LocalDateTime localdatetime) {
		super();
		this.customerId = customerId;
		this.uuid = uuid;
		this.localdatetime = localdatetime;
	}
	public CustomerUserSession() {
		
	}
	
	
}
