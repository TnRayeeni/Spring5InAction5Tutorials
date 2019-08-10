/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.dao;

import org.springframework.data.repository.CrudRepository;

import com.arnab.spring.api.entity.User;
/**
 * This interface is DAO/Repository interface.
 * In database there is table named - user. This interface maps that table with the class User. The primary key
 * is maintained as Long/BigInt in class/table, which can bess seen below. This relies on the default names by mapping the table
 * name with the class name and the fields names with the class members' names.
 * For UserDetailsService, a specific method is needed through which a user can be identified by username - findByUsername.
 * The method is declared below. This method is accessed in our custom UserDetailsService - MyUserDetailsService.
 * @author Arnab
 *
 */
public interface UserDao extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
