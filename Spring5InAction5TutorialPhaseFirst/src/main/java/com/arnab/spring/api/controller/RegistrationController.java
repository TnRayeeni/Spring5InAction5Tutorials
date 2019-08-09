package com.arnab.spring.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnab.spring.api.domain.RegistrationForm;
import com.arnab.spring.api.service.RegistrationService;

@Controller
@RequestMapping("/pizza/register")
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;
	
	@GetMapping
	public String registerForm() {
		return service.registerForm();
	}
	
	@PostMapping
	public String processRegistration(RegistrationForm form) {
		return service.processRegistration(form);
	}

}
