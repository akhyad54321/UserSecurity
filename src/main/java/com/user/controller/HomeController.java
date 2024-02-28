package com.user.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public String user1(){
        return "This is User Page";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "This is Admin Page";
    }

    @PreAuthorize("hasRole('Management')")
    @GetMapping("/management")
    public String management(){
        return "This is Management Page";
    }

}