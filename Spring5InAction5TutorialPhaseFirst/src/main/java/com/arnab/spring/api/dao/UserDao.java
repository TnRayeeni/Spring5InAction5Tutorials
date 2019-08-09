package com.arnab.spring.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.arnab.spring.api.domain.User;

public interface UserDao extends CrudRepository<User, Long> {

	User findByUsername(String username);
	
}
