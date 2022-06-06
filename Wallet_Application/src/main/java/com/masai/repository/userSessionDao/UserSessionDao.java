package com.masai.repository.userSessionDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.UserSession;
@Repository
public interface UserSessionDao extends JpaRepository<UserSession, String>{
	
		public UserSession findByUuid(String uuid);
		
		
	
}
