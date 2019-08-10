/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.arnab.spring.api.dao.UserDao;
import com.arnab.spring.api.domain.AppUser;
import com.arnab.spring.api.entity.User;

/**
 * This is the UserDetailsService implementation. This accesses the DAO to fetch the user details by username and then
 * creates a AppUser, which is basically a Spring Security UserDetails. Then it returns the new AppUser.
 * This object is accessed by Spring Security internally while it intercepts the requests. So this object is injected
 * in the SecurityConfig.
 * @author Arnab
 *
 */

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = dao.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("Error 404: User not found!");
		}
		return new AppUser(user);
	}

}
