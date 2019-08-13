/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.domain;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.arnab.spring.api.entity.User;

/**
 * This class denotes a User in Spring Security Context. The User object which is created earlier denotes a app user.
 * That user object is converted to this object. Then it can be used by Spring Security. As you can see this class implements
 * UserDetails interface of Spring Security. It identifies this class as a Spring Security UserDetails class.
 * This class holds the username, password, roles, and other few items, which are used for authentication and authorization.
 * @author Arnab
 *
 */

public class AppUser implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private User user;	

	public AppUser(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
