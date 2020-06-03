package com.authlogin.spring.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authlogin.spring.model.Role;
import com.authlogin.spring.model.User;
import com.authlogin.spring.repository.UserRepository;
import com.authlogin.spring.web.dto.UserRegistrationDto;

@Service
public class UserServiceImpl implements  UserService{
	
	@Autowired
private UserRepository userrepository;
	
	@Autowired
	private BCryptPasswordEncoder  passwordencoder;
	
	
	public User findByEmail(String email)
	{
		
		return userrepository.findByEmail(email);
	}

	
	
	public User save(UserRegistrationDto registration){
        User user = new User();
        user.setFirstName(registration.getFirstName());
        user.setLastName(registration.getLastName());
        user.setEmail(registration.getEmail());
        user.setPassword(passwordencoder.encode(registration.getPassword()));
        user.setRole(Arrays.asList(new Role("ROLE_USER")));
        return userrepository.save(user);
    }


	public UserDetails  loadUserByUsername(String email)
	{
		
		User user = userrepository.findByEmail( email);
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
		
	
	}
	
	
	private Collection<?extends GrantedAuthority > mapRolesToAuthorities(Collection<Role>  roles)
	{
		
		return roles.stream()
				.map(role -> new  SimpleGrantedAuthority(role.getName()))
						.collect(Collectors.toList());
				
		
		
				
		
	}

}
