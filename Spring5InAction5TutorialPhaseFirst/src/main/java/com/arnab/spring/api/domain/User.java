package com.arnab.spring.api.domain;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "pizza_users")
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "enabled")
	private int enabled;
	
	@Column(name = "cred_non_expired")
	private int credentialsNonExpired;
	
	@Column(name = "account_non_locked")
	private int accountNonLocked;
	
	@Column(name = "account_non_expired")
	private int accountNonExpired;
	
	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "phone")
	private String phoneNumber;

	public User() {
		super();
	}

	public User(String username, String password, int enabled, int credentialsNonExpired, int accountNonLocked,
			int accountNonExpired, String fullName, String city, String phoneNumber) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.accountNonExpired = accountNonExpired;
		this.fullName = fullName;
		this.city = city;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Arrays.asList(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public boolean isAccountNonExpired() {
		return (accountNonExpired == 1) ? true : false;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public void setCredentialsNonExpired(int credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setAccountNonLocked(int accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setAccountNonExpired(int accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return (accountNonLocked == 1) ? true : false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return (credentialsNonExpired == 1) ? true : false;
	}

	@Override
	public boolean isEnabled() {
		return (enabled == 1) ? true : false;
	}

	@Override
	public String getPassword() {
		return username;
	}

	@Override
	public String getUsername() {
		return password;
	}

}
