package com.user.controller;

import com.user.entity.User;
import com.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @PostMapping
    public User createUser(@RequestBody User user){
        log.info("UserController - Inside createUser method");
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUser(){
        log.info("UserController - Inside getAllUser method");
        return userService.getAllUser();

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id){
        log.info("UserController - Inside getUserById method");
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Integer id){
        log.info("UserController - Inside deleteUserById method");
        userService.deleteUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUserById(@PathVariable("id") Integer id, @RequestBody User user){
        log.info("UserController - Inside updateUserById method");
        return userService.updateUserById(id, user);
    }

}
