package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.CustomerUserSession;

public interface SessionDao extends JpaRepository<CustomerUserSession,Long>{
	
	public Optional<CustomerUserSession>  findByCustomerId(long customerId);
	
	public Optional<CustomerUserSession>  findByUuid(String uuid);
	
	

}
