package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserSecurityApplication implements CommandLineRunner {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(UserSecurityApplication.class, args);
		System.out.println("Hello Ganeshaa!!!");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(bCryptPasswordEncoder.encode("Password@123"));
	}
}
