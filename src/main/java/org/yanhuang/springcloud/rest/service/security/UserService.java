/**
 * 
 */
package org.yanhuang.springcloud.rest.service.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;
import org.yanhuang.springcloud.rest.jpa.repo.security.UserRepository;

/**
 * @author zhyhang
 *
 */
@Component
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User u = repo.findByUsername(username).orElse(null);
		if (u != null) {
			return u;
		}
		throw new UsernameNotFoundException(username);
	}
	
	public User create(User user) {
		repo.findByUsername(user.getUsername()).ifPresent(u->{
			throw new RuntimeException("user exists");
		});
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	
	public Optional<User> find(Long id) {
		return repo.findById(id);
	}

}
