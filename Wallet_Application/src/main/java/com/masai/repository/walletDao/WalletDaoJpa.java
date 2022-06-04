package com.masai.repository.walletDao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.entity.Wallet;


public interface WalletDaoJpa extends JpaRepository<Wallet, Integer> {

}
