package com.masai.repository.customerDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer,String> {

	//public Customer findByMobileNumber(String mobileNumber, String password);

	
	//public Customer findByMobileNumber(String mobileNumber);
	

}
