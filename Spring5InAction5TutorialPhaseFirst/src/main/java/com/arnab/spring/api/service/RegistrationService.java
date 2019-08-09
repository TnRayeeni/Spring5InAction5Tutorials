package com.arnab.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arnab.spring.api.dao.UserDao;
import com.arnab.spring.api.domain.RegistrationForm;

@Service
public class RegistrationService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public String registerForm() {
	return "register";
	}
	
	public String processRegistration(RegistrationForm form) {
	dao.save(form.toUser(passwordEncoder));
	return "redirect:/login";
	}
	
}
