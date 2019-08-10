package com.arnab.spring.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arnab.spring.api.dao.UserDao;
import com.arnab.spring.api.domain.User;

@Service
public class RegistrationService {

	@Autowired
	private UserDao dao;

	@Autowired
	private PasswordEncoder encoder;

	public String registerForm() {
		return "/register.html";
	}

	public String processRegistration(User user) {
		com.arnab.spring.api.entity.User daoUser = new com.arnab.spring.api.entity.User();
		daoUser.setUsername(user.getUsername());
		daoUser.setPassword(encoder.encode(user.getPassword()));
		dao.save(daoUser);
		return "redirect:/login";
	}

}
