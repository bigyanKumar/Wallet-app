package com.masai.entity;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Wallet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Min(value=0, message="Balance should be greate than 0")
	private Double balance;
	@OneToMany(targetEntity = Transaction.class,cascade = CascadeType.ALL)
	@JoinColumn(name="walletId",referencedColumnName = "id" )
	private List<Transaction> tran;
}





















