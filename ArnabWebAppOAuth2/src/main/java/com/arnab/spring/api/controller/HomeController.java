/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String home() {
		return "home.jsp";
	}
	
	@RequestMapping(value = "/login")
	public String loginPage() {
		return "login.jsp";
	}
	
	@RequestMapping(value = "/logout-success")
	public String logoutPage() {
		return "logout.jsp";
	}
	
}
