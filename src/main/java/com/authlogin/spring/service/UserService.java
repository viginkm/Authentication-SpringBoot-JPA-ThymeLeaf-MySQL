package com.authlogin.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.authlogin.spring.model.User;
import com.authlogin.spring.web.dto.UserRegistrationDto;

public interface UserService  extends  UserDetailsService{
	
	
	User findByEmail(String email);
	User save(UserRegistrationDto registration);
	
	

}
