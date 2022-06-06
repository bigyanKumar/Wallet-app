package com.masai.entity;


import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Transaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer transactionId;
	private String transactionType;
	private LocalDateTime dateTime;
	private Double amount;
	private String description;
}
