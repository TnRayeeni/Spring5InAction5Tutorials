/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	/**
	 * This is for the OAuth2.
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/user")
	@ResponseBody
	public Principal user(Principal principal) {
		return principal;
	}
	
}
