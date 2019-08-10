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
