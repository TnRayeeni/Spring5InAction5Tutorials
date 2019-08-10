package com.arnab.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnab.spring.api.domain.User;
import com.arnab.spring.api.service.RegistrationService;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;

	@GetMapping
	public String getRegister() {
		return service.registerForm();
	}
	
	@PostMapping
	public String processRegistration(User user) {
		return service.processRegistration(user);
	}
	
}
