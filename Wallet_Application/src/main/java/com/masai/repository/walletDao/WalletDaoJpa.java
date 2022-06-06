package com.masai.repository.walletDao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.entity.Wallet;

@Repository
public interface WalletDaoJpa extends JpaRepository<Wallet, Integer> {

}
