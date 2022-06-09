package com.masai.DTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginDTO {
	
	@NotNull(message="Mobile shouldn't be null.")
	@Size(min=10,max=10, message="length should not be less then 10 and greater then 10")
	
	private String mobileNumber;
	@NotNull(message="password shouldn't be null.")
	private String password;

}
