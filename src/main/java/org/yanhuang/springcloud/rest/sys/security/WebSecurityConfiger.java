package org.yanhuang.springcloud.rest.sys.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.StaticResourceRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yanhuang.springcloud.rest.service.security.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiger extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private FormLoginSuccessHandler loginSuccessHandlerForm;
	
	@Autowired
	private LogoutHandler logoutHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* @formatter:off */

		http.authorizeRequests()
		.requestMatchers(StaticResourceRequest.toCommonLocations()).permitAll()
		.anyRequest().fullyAuthenticated()
		.and().formLogin()
		.loginPage("/login").permitAll()
		.successHandler(loginSuccessHandlerForm)
		.and().logout().permitAll()
		.addLogoutHandler(logoutHandler)
		.and().headers().frameOptions().disable() // (all frame nest) for h2-console
		.and().csrf().disable(); // for h2-console
		
//		http.sessionManagement();
		/**
		 * https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
		 * <br>http://127.0.0.1:8080/h2-console/
		 * </br>see the spring-boot start default mem db:
		 * </br>jdbc:h2:mem:testdb
		 * </br>https://stackoverflow.com/questions/7309359/view-content-of-h2-or-hsqldb-in-memory-database
		 */

		/* @formatter:on */

	}
	
	/**
	 * Listening authentication event and log
	 * @return
	 */
	@Bean
	public org.springframework.security.authentication.event.LoggerListener authenEventListener() {
		return new org.springframework.security.authentication.event.LoggerListener();
	}
	
	/**
	 * Listening authorization event and log
	 * @return
	 */
	@Bean
	public org.springframework.security.access.event.LoggerListener authorEventListener(){
		return new org.springframework.security.access.event.LoggerListener();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
	}


}