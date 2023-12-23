package com.example.be.service.impl;

import com.example.be.model.ChangePassword;
import com.example.be.model.User;
import com.example.be.repository.UserRepository;
import com.example.be.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUserName(String username) {
        if (!userRepository.findByUsername(username).isPresent()) {
            return null;
        }
        return userRepository.findByUsername(username).get();
    }

    @Override
    public boolean checkLogin(User user) {
        Iterable<User> users = this.findAll();
        boolean isCorrectUser = false;
        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername())
                    && user.getPassword().equals(currentUser.getPassword())
            ) {
                isCorrectUser = true;
            }
        }
        return isCorrectUser;
    }

    @Override
    public boolean isRegisted(User user) {
        Iterable<User> userIterable = this.findAll();
        boolean isRegisted = false;
        for (User currentUser : userIterable) {
            if (user.getUsername().equals(currentUser.getUsername())) {
                isRegisted = true;
                break;
            }
        }
        return isRegisted;
    }

    @Override
    public boolean isCorrectConfirmPassword(User user) {
        boolean isCorrectConfirmPassword = false;
        if (user.getPassword().equals(user.getConfirmPassword())) {
            isCorrectConfirmPassword = true;
        }
        return isCorrectConfirmPassword;
    }

    @Override
    public boolean changePassword(Long id, ChangePassword changePassword) {
        boolean allowChangePassword = false;
        Optional<User> userOptional = userRepository.findById(id);
        String oldPassword = changePassword.getOldPassword();
        String newPassword = changePassword.getNewPassword();
        String newComfirmPassword = changePassword.getNewComfirmPassword();
        if (!userOptional.isPresent()) {
            return false; // Unable to change password
        }
        User user = userOptional.get();
        if(!newPassword.equals(newComfirmPassword)){
            return false;
        }
        if (user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            user.setConfirmPassword(newComfirmPassword);
            userRepository.save(user);
            allowChangePassword = true; // Password changed successfully
        }
        return allowChangePassword;
    }

    @Override
    public boolean changInformation(Long id, User user) {
        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            return false; // Unable to change password
        }
        User currentUser = userOptional.get();
        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
        userRepository.save(currentUser);
        return true;
    }

    @Override
    public boolean deleteMyUser(Long id, Boolean check) {
        if(!check){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}
