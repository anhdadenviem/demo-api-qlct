package com.example.be.controller;

import com.example.be.model.User;
import com.example.be.model.Wallet;
import com.example.be.service.UserService;
import com.example.be.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    WalletService walletService;
    UserService userService;
    @GetMapping
    public ResponseEntity getWallets(){
        return new ResponseEntity<>(walletService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getWallet(@PathVariable Long id){
        Optional<Wallet> walletOptional = walletService.findById(id);
        return walletOptional.map(wallet -> new ResponseEntity<>(wallet, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/wallet/{idUser}")
    public ResponseEntity getMyWallet(@PathVariable Long idUser){
        List<Wallet> wallets = walletService.findAllByUser_Id(idUser);
        if(wallets.isEmpty()){
            return new ResponseEntity<>("Bạn chưa có Ví nào",HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wallets,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity addNew(@RequestBody Wallet wallet){
        if(wallet.getName().isEmpty() || wallet.getBalance() == 0 || wallet.getCurrency().isEmpty()){
            return new ResponseEntity<>("Thiếu thông tin ví", HttpStatus.BAD_REQUEST);
        }
        Iterable<Wallet> wallets = walletService.findAll();
        for(Wallet currentWallet : wallets){
            if(currentWallet.getName().equals(wallet.getName())){
                return new ResponseEntity<>("Ví đã có sẵn",HttpStatus.BAD_REQUEST);
            }
        }
        walletService.save(wallet);
        return new ResponseEntity<>("Đã thêm ví thành công",HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id ,@RequestBody Wallet wallet){
        wallet.setId(id);
        walletService.save(wallet);
        return new ResponseEntity("Đã sửa thông tin ví", HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        walletService.remove(id);
        return new ResponseEntity<>("Đã xóa Ví",HttpStatus.OK);
    }
    @PostMapping("/share/{idWallet}")
    public ResponseEntity share(@PathVariable("idWallet") Long id,@RequestBody User user){
        walletService.shareWallet(id,user);
        return new ResponseEntity<>("Đã Share",HttpStatus.OK);
    }

}
