package org.yanhuang.springcloud.rest.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiger extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/* @formatter:off */

		// production

//		http.authorizeRequests()
//		.requestMatchers(StaticResourceRequest.toCommonLocations()).permitAll()
//		.anyRequest().fullyAuthenticated()
//		.and().formLogin()
//		.loginPage("/login").permitAll()
//		.and().logout().permitAll();
		
		// h2-console can run nornally
		/**
		 * https://springframework.guru/using-the-h2-database-console-in-spring-boot-with-spring-security/
		 * <br>http://127.0.0.1:8080/h2-console/
		 * </br>see the spring-boot start default mem db:
		 * </br>jdbc:h2:mem:testdb
		 * </br>https://stackoverflow.com/questions/7309359/view-content-of-h2-or-hsqldb-in-memory-database
		 */
		
		http.authorizeRequests()
		.antMatchers("/**").permitAll()
		.and().authorizeRequests().antMatchers("/h2-console/**").permitAll()
		.and().headers().frameOptions().disable()
		.and().csrf().disable();
		
		
		/* @formatter:on */

	}

	@Bean
	public InMemoryUserDetailsManager InMemoryUserDetailsManager() {
		return new InMemoryUserDetailsManager(
				User.withDefaultPasswordEncoder().username("admin").password("88888888").roles("ADMIN", "USER").build(),
				User.withDefaultPasswordEncoder().username("user").password("666666").roles("USER").build());
	}

	/**
	 * Compatible history and future
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put(idForEncode, new BCryptPasswordEncoder(8));
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder());
		encoders.put("sha256", new StandardPasswordEncoder());
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}

}