package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.BillPayment;
import com.masai.entity.Wallet;

public interface WalletDao extends JpaRepository<Wallet, Integer>{

}
