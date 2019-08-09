package com.arnab.spring.api.domain;

import org.springframework.security.crypto.password.PasswordEncoder;

public class RegistrationForm {

	private String username;
	private String password;
	private String fullName;
	private String city;
	private String phoneNumber;

	public RegistrationForm() {
		super();
	}

	public RegistrationForm(String username, String password, String fullName, String city, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.city = city;
		this.phoneNumber = phoneNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public User toUser(PasswordEncoder encoder) {
		return new User(this.username, "{bcrypt}" + encoder.encode(this.password), 1, 1, 1, 1, this.fullName, this.city, this.phoneNumber);
	}

}
