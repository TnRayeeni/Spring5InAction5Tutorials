/*
 * Copyright (c) 2019, ARNAB BANERJEE. All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted only for academic purposes.
 * 
 * For further queries / info: arnab.ban09@gmail.com
 */

package com.arnab.spring.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	/**
	 * In case of database/JDBC based authentication and authorization, you have to provide a UserDetailsService instance
	 * which will implement the UserDetailsService interface. This interface is provided by the Spring Security.
	 * It holds the identity of a user. In case of in-memory authentication, we have used the default implementation of
	 * this interface. 
	 */
	@Autowired
	private UserDetailsService userDetailsService;
	
	/**
	 * The below method is used to use a database, like MySQL, to access the username and password. Means
	 * it is using a database for authentication and authorization. This is basically JDBC-based
	 * authentication and authorization. You can see the UserDetailsService is provided in the AuthenticationProvider.
	 * This UserDetailsService object is actually MyUserDetailsService which implements the UserDetailsService interface by 
	 * Spring Security. This is responsible for fetching the data from the database for a particular user based on a username.
	 * The UserDetailsService is mentioned above. Then the password encoder is provided.
	 * @return
	 */	
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		
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
		provider.setPasswordEncoder(this.encoder());
		
		return provider;
	}
	
	/**
	 * This method creates a bean of PasswordEncoder. This PasswordEncoder bean is then injected in above setPasswordEncoder method.
	 * And also this autowires with any PasswordEncoder instance thoughout the app.
	 * @return
	 */
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * The below method is actually configuring the security at HTTP level and securing the URLs at which requests will land.
	 * 1. It will disable the CSRF.
	 * 2. It will authorize all requests coming to this app through 'login' url.
	 * 3. It will skip authenticate and authorize for url - 'register' so that anonymous user can register.
	 * 4. It will authenticate all the requests.
	 * 5. It authenticates, provide a form to provide login credentials.
	 * 6. It implements logout feature and provide a url after successful logout.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/login").permitAll()
			.antMatchers("/register").anonymous()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").permitAll()
			.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/logout-success").permitAll();
	}

	/*
	 * @Override public void configure(WebSecurity web) throws Exception {
	 * web.ignoring().antMatchers("/register", "/register.html"); }
	 */	
	
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
