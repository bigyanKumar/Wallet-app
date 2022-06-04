package com.masai.repository.customerDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Wallet;

@Repository
public interface DaoWallet extends JpaRepository<Wallet,Integer> {

}
