package com.masai.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.masai.DTO.CustomerDTO;
import com.masai.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,String> {	
	
	 @Query("select new com.masai.DTO.CustomerDTO(c.name, c.mobileNumber, w.balance)"
		  		+ " from Customer c, Wallet w where c.wallet.id=?1 AND w.id=?2")
		           public CustomerDTO viewBalance(Integer id1,Integer id2);

}
