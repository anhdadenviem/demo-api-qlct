package com.example.be.controller;

import com.example.be.model.ChangePassword;
import com.example.be.model.User;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity getList() {
        return new ResponseEntity(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable Long id) {
        Optional<User> userOptional = this.userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        //        Cách hiểu:
//        if(userOptional.isPresent()){
//            User user = userOptional.get();
//            return new ResponseEntity<>(user,HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        if (userService.isRegisted(user)) {
            return new ResponseEntity<>("Tài khoản đã tồn tại", HttpStatus.BAD_REQUEST);
        }
        if (!userService.isCorrectConfirmPassword(user)) {
            return new ResponseEntity<>("Mật khẩu và nhập lại mật khẩu chưa trùng khớp", HttpStatus.BAD_REQUEST);
        }
        user.setAdmin(false);
        userService.save(user);
        return new ResponseEntity<>("Đăng ký thành công", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody User user) {
        if (!userService.checkLogin(user)) {
            return new ResponseEntity<>("Đăng nhập không thành công", HttpStatus.BAD_REQUEST);
        }
        User userLogin = userService.findByUserName(user.getUsername());
        return new ResponseEntity<>(userLogin, HttpStatus.OK);
    }

    @PutMapping("/changePassword/{id}")
    public ResponseEntity changePassword(@PathVariable Long id,@RequestBody ChangePassword changePassword) {
        if (userService.changePassword(id,changePassword)) {
            return new ResponseEntity<>("Thay đổi mật khẩu thành công",HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Mật khẩu cũ sai hoặc mật khẩu mới nhập lại không trùng với mật khẩu mới ",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity changeInfomation(@PathVariable Long id,@RequestBody User user){
        if (!userService.changInformation(id,user)) {
            return new ResponseEntity<>("Không tìm thấy người dùng",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Đã thay đổi thông tin",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMyUser(@PathVariable Long id,@RequestBody boolean check) {
        Optional<User> userOptional = userService.findById(id);
        if(!userOptional.isPresent()) return new ResponseEntity<>("Không tìm thấy ngườ dùng", HttpStatus.NOT_FOUND);
        if (!userService.deleteMyUser(id,check)) {
            return new ResponseEntity<>("Chưa xóa account",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Đã xác nhận và xóa account",HttpStatus.OK);
    }

}
