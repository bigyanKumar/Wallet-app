package com.masai.customerService.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Customer;

@Repository
public interface WalletDao extends JpaRepository<Customer,String> {
	

}
