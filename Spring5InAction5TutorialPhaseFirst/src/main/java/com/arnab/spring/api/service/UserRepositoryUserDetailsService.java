package com.arnab.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arnab.spring.api.dao.UserDao;
import com.arnab.spring.api.domain.User;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found!");
		} 
		System.out.println("PASSWORD: " + user.getPassword());
		System.out.println("ENABLED? : " + user.isEnabled());
		String pass = encoder.encode(user.getPassword());
		user.setPassword(pass);
		System.out.println("PASSWORD: " + encoder.encode(user.getPassword()));
		//String password = user.getPassword().startsWith("{bcrypt}") ? user.getPassword() : encoder.encode(user.getPassword());
		//user.setPassword(password);		
		//return new org.springframework.security.core.userdetails.User(user.);
		return new org.springframework.security.core.userdetails.User(user.getUsername(), encoder.encode(user.getPassword()), user.getAuthorities());
	}

}
