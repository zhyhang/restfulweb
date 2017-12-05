/**
 * 
 */
package org.yanhuang.springcloud.rest.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;
import org.yanhuang.springcloud.rest.service.security.UserService;
import org.yanhuang.springcloud.rest.util.SecurityUtils;

/**
 * @author zhyhang
 *
 */
@Controller
public class SecurityController {

	@Autowired
	private UserService service;

	@GetMapping(value = { "/login", "/login/;JSESSIONID={sessionId}" })
	public String loginView() {
		if (SecurityUtils.hasLogin()) {
			return "redirect:/";
		}
		return "login.html";
	}

	@PostMapping({ "/user/", "/user" })
	@ResponseBody
	public ResponseEntity<?> create(@Valid @RequestBody User user) {
		User created = service.create(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/").buildAndExpand(created.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@GetMapping("/user/{id}/")
	@ResponseBody
	public User find(@PathVariable Long id) {
		return service.find(id).orElse(null);
	}

}
