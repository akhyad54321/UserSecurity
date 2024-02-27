package com.user.controller;


import com.user.config.CustomUserDetailsService;
import com.user.exception.NotFoundException;
import com.user.request.JWTAuthRequest;
import com.user.response.JWTAuthResponse;
import com.user.security.JwtTokenHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenHelper tokenHelper;
    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody JWTAuthRequest request) {
        log.info("AuthController - Inside login method");
        authenticate(request.getUsername(), request.getPassword());
        UserDetails userDetails = customUserDetailService.loadUserByUsername(request.getUsername());
        String generatedToken = tokenHelper.generateToken(userDetails);
        System.out.println("generatedToken = " + generatedToken);

        JWTAuthResponse response = new JWTAuthResponse();
        response.setToken(generatedToken);
        return new ResponseEntity<JWTAuthResponse>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (Exception e) {
            System.out.println(e);
            throw new NotFoundException("Invalid Username or Password!!!");
        }
    }

}
