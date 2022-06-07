package com.masai.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillPaymentDTO {
	
	

	private Integer billId;
	private String billType;
	private Double amount;
	private LocalDateTime paymentDate;

}
