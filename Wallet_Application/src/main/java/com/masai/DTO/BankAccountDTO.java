package com.masai.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BankAccountDTO {
	
	private Integer accountNo;
	
	private String ifscCode;
	
	private String bankName;
	private Double bankBalance;
	

}
