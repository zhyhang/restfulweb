/**
 * 
 */
package org.yanhuang.springcloud.rest.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;
import org.yanhuang.springcloud.rest.service.security.UserService;

/**
 * @author zhyhang
 *
 */
@RestController
public class SecurityController {
	
	@Autowired
	private UserService service;
	
	@PostMapping({"/user/","/user"})
	public ResponseEntity<?> create(@RequestBody User user) {
		User created=service.create(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/").buildAndExpand(created.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
