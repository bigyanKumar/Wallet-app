package com.masai.entity;
import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

import org.springframework.context.annotation.Bean;

import com.masai.globalExceptionHandler.CostumerNotFoundException;

import antlr.collections.List;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public java.util.List<Customer> getTran() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public synchronized void addBalance(double balance) {
		this.balance+=balance;
	}
	public synchronized double deductBalance(@Min(value = 0, message = "Balance should be greate than 0") double balance) throws CostumerNotFoundException{
		
		if(this.balance>=balance) {
			this.balance-=balance;
			return balance;
		}else {
			throw new CostumerNotFoundException("insufficient amount in wallet");
		}
	}
}





















