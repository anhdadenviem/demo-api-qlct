package com.example.be.service;

import com.example.be.model.User;
import com.example.be.model.Wallet;

import java.util.List;

public interface WalletService extends IService<Wallet>{
    void shareWallet(Long idWallet, User user);
    List<Wallet> findAllByUser_Id(Long id);
}

