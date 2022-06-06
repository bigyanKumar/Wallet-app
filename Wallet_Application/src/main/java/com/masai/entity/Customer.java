package com.masai.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
	
	@Id
	@Min(value=9, message="Mobile should be 10 digit")
	private String mobileNumber;
	@NotNull(message="Name should be not null")
	private String name;
	@NotNull(message="Password Should be not Null")
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
}
