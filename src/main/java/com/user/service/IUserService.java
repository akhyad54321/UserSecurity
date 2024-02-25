package com.user.service;

import com.user.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(User user);
    List<User> getAllUser();
    User getUserById(Integer id);
    void deleteUserById(Integer id);
    User updateUserById(Integer id, User user);

}
