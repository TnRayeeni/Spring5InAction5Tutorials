package com.arnab.spring.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * @Autowired private DataSource dataSource;
	 * 
	 * private static final String USERS_QUERY =
	 * "SELECT USERNAME, PASSWORD, ENABLED FROM PIZZA_USERS WHERE USERNAME=?";
	 * private static final String AUTHORITY_QUERY =
	 * "SELECT USERNAME, AUTHORITY FROM PIZZA_USERS_AUTHORITIES WHERE USERNAME=?";
	 */

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(USERS_QUERY)
		//		.authoritiesByUsernameQuery(AUTHORITY_QUERY).passwordEncoder(encoder());

		//auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
		
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeRequests() .antMatchers("/login").permitAll() .and()
		 * .formLogin().defaultSuccessUrl("/home.html").permitAll() .and().logout()
		 * .permitAll().and().csrf().disable();
		 */
		http.csrf().disable()
		.authorizeRequests().antMatchers("/login").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().defaultSuccessUrl("/home.html").permitAll()
		.and()
		.logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.permitAll();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder encoder() {

		/*
		 * Map<String, PasswordEncoder> encoders = new HashMap<>();
		 * encoders.put("bcrypt", new BCryptPasswordEncoder());
		 * 
		 * PasswordEncoder defaultEncoder = new BCryptPasswordEncoder();
		 * 
		 * DelegatingPasswordEncoder encoder = new DelegatingPasswordEncoder("bcrypt",
		 * encoders); encoder.setDefaultPasswordEncoderForMatches(defaultEncoder);
		 * 
		 * return encoder;
		 */

		return new BCryptPasswordEncoder();
	}

}
