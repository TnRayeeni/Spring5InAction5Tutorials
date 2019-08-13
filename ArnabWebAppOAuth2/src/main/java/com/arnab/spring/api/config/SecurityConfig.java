/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This project is to demonstrate the OAuth2.0. So please read carefully.
 * Added the @EnableOAuth2Sso annotation on top of the SecurityConfig class.
 * Rest are explained in code below.
 * Also as the previous project demonstrated the Spring Security, few things are commented here as we are using OAuth2.
 * @author Arnab
 *
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso 	// To enable OAuth2 on top of Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * As we are working with OAuth2, we don't need few things as we needed earlier when we demonstrated Spring Security.
	 * So here, we don't need the UserDetailsService too. So commenting it out. For further details about this, 
	 * please refer the below comment or the earlier Spring Security project - ArnabWebApp. 
	 */
	/**
	 * In case of database/JDBC based authentication and authorization, you have to provide a UserDetailsService instance
	 * which will implement the UserDetailsService interface. This interface is provided by the Spring Security.
	 * It holds the identity of a user. In case of in-memory authentication, we have used the default implementation of
	 * this interface. 
	 */
	//@Autowired
	//private UserDetailsService userDetailsService;
	
	// THE BELOW CODE IS FOR DATABASE/JDBC BASED AUTHENTICATION. PLEASE READ CAREFULLY.
	
	/**
	 * So here, we don't need the authProvider() method too. So commenting it out. For further details about this, 
	 * please refer the below comment or the earlier Spring Security project - ArnabWebApp. 
	 */
	/**
	 * The below method is used to use a database, like MySQL, to access the username and password. Means
	 * it is using a database for authentication and authorization. This is basically JDBC-based
	 * authentication and authorization. You can see the UserDetailsService is provided in the AuthenticationProvider.
	 * This UserDetailsService object is actually MyUserDetailsService which implements the UserDetailsService interface by 
	 * Spring Security. This is responsible for fetching the data from the database for a particular user based on a username.
	 * The UserDetailsService is mentioned above. Then the password encoder is provided.
	 * @return
	 */	
	/*@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);*/
		
		/**
		 * Here the password encoder is provided a NoOpPasswordEncoder. This is a simple password encoder which
		 * treats passwords as just text, not doing any encryption. So in real life scenarios, this is bad.
		 * So it is commented out now. To test it, just uncomment it and comment the BCryptPasswordEncoder.
		 */
		//provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		
		/**
		 * Here the password encoder is provided a BCryptPasswordEncoder. This is a BCrypt password encoder which
		 * treats passwords as BCrypt hashes. So in real life scenarios, this is good.
		 * To test, there is an online tool to generate BCrypt hash passwords from plain text passwords -
		 * https://www.browserling.com/tools/bcrypt
		 * Just generate it and insert into you database table and then test.
		 */
		/*provider.setPasswordEncoder(this.encoder());
		
		return provider;
	}*/
	
	/**
	 * So here, we don't need the PasswordEncoder too. So commenting it out. For further details about this, 
	 * please refer the below comment or the earlier Spring Security project - ArnabWebApp. 
	 */
	/**
	 * This method creates a bean of PasswordEncoder. This PasswordEncoder bean is then injected in above setPasswordEncoder method.
	 * And also this autowires with any PasswordEncoder instance thoughout the app.
	 * @return
	 */
	/*@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}*/

	/**
	 * The below method is actually configuring the security at HTTP level and securing the URLs at which requests will land.
	 * 1. It will disable the CSRF.
	 * 2. It will authorize all requests coming to this app through 'login' url.
	 * 3. It will authenticate all the requests via OAuth2 using Google APIs.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.anyRequest().authenticated();
	}	
	
	// THIS BELOW CODE IS FOR IN-MEMORY AUTHENTICATION. PLEASE READ CARFULLY.
	
	/**
	 * The below mechanism is used to demonstrate the in-memory authentication and authorization.
	 * This is commented now as we are using some other method. To test this mechanism, please uncomment and and comment
	 * other mechanism and run the application. Also import the required classes.
	 */	
	/*@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return this.getUsersFromInMemory();
	}*/		
	/**
	 * The below method returns some users' credentials. It is using an in-memory user details manager.
	 * Doing in-memory authentication and authorization.
	 * This is just for demo purposes and not suggested to use in real scenarios.
	 * @return
	 */
	/*@SuppressWarnings("deprecation")
	private UserDetailsService getUsersFromInMemory() {
		List<UserDetails> users = new ArrayList<UserDetails>();
		users.add(User.withDefaultPasswordEncoder().username("arnab").password("arnab").roles("USER").build());
		users.add(User.withDefaultPasswordEncoder().username("gopal").password("gopal").roles("USER").build());
		return new InMemoryUserDetailsManager(users);
	}*/
	
}
