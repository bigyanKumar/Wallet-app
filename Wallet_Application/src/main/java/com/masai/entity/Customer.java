package com.masai.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
	@Pattern(regexp = "[0-9]{10}",message="Mobile no invalid")
	private String mobileNumber;
	
	@NotNull(message="Name should not be null")
	private String name;
	@NotNull(message="Password Should not be null")
	@Size(min=5,max=8,message="Password should be min 5 and max 8")
	private String password;
	@OneToOne(cascade = CascadeType.ALL)
	private Wallet wallet;
	
}
