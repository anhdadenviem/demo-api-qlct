package com.example.be.repository;

import com.example.be.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletRepository extends JpaRepository<Wallet,Long> {
    List<Wallet> findAllByUser_Id(Long id);
}
