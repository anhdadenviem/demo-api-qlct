package com.example.be.service;

import com.example.be.model.ChangePassword;
import com.example.be.model.User;

public interface UserService extends IService<User>{
    User findByUserName(String name);
    boolean checkLogin(User user);
    boolean isRegisted(User user);
    boolean isCorrectConfirmPassword(User user);
    boolean changePassword(Long id, ChangePassword changePassword);
    boolean changInformation(Long id,User user);
    boolean deleteMyUser(Long id,Boolean check);
}
