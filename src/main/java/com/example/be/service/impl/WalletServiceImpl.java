package com.example.be.service.impl;

import com.example.be.model.User;
import com.example.be.model.Wallet;
import com.example.be.repository.UserRepository;
import com.example.be.repository.WalletRepository;
import com.example.be.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletRepository walletRepository;
    UserRepository userRepository;

    @Override
    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }

    @Override
    public Iterable<Wallet> findAll() {
        return walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> findById(Long id) {
        return walletRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        walletRepository.deleteById(id);
    }


    @Override
    public void shareWallet(Long idWallet, User user) {
        if(user == null){
            return;
        }
        Optional<Wallet> walletOptional = walletRepository.findById(idWallet);
        if (!walletOptional.isPresent()) {
           return;
        }
        Wallet wallet = walletOptional.get();
        Wallet walletShare = new Wallet();
        walletShare.setUser(user);
        walletShare.setName(wallet.getName());
        walletShare.setBalance(wallet.getBalance());
        walletShare.setCurrency(wallet.getCurrency());
        walletShare.setDescription(wallet.getDescription());
        walletRepository.save(walletShare);
    }

    @Override
    public List<Wallet> findAllByUser_Id(Long id) {
        return walletRepository.findAllByUser_Id(id);
    }
}
