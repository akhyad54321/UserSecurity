package com.user.service.impl;

import com.user.entity.User;
import com.user.exception.NotFoundException;
import com.user.repository.UserRepository;
import com.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        log.info("UserServiceImpl - Inside createUser method ");
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        log.info("UserServiceImpl - Inside getAllUser method ");
        Iterable<User> userList = userRepository.findAll();
        return (List<User>) userList;
    }

    @Override
    public User getUserById(Integer id) {
        log.info("UserServiceImpl - Inside getUserById method ");
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("User Not Found");
        }
        User user = optionalUser.get();
        return user;
    }

    @Override
    public void deleteUserById(Integer id) {
        log.info("UserServiceImpl - Inside deleteUserById method ");
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(Integer id, User user) {
        log.info("UserServiceImpl - Inside updateUserById method ");
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new NotFoundException("User Not Found");
        }
        User updateUser = optionalUser.get();
        updateUser.setName(user.getName());
        updateUser.setContactNo(user.getContactNo());
        updateUser.setUsername(user.getUsername());
        updateUser.setPassword(user.getPassword());
        updateUser.setRole(user.getRole());
        return userRepository.save(updateUser);
    }
}
