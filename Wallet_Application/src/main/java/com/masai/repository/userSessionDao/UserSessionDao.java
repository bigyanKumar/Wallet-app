package com.masai.repository.userSessionDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.UserSession;

public interface UserSessionDao extends JpaRepository<UserSession, String>{
	
		public UserSession findByUuid(String mobile);
		
		
	
}
