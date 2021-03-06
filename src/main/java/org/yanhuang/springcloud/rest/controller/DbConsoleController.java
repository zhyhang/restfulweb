/**
 * 
 */
package org.yanhuang.springcloud.rest.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.yanhuang.springcloud.rest.jpa.a.domain.Employee;
import org.yanhuang.springcloud.rest.jpa.b.domain.Company;
import org.yanhuang.springcloud.rest.jpa.domain.Person;
import org.yanhuang.springcloud.rest.jpa.domain.o.Nation;
import org.yanhuang.springcloud.rest.service.DbConsoleService;
import org.yanhuang.springcloud.rest.util.JsonUtils;

/**
 * database console
 * 
 * @author zhyhang
 *
 */
@RestController
public class DbConsoleController {

	private static final Logger logger = LoggerFactory.getLogger(DbConsoleController.class);

	@Autowired
	private DbConsoleService service;

	@GetMapping("/allperson")
	public List<Map<String, Object>> queryAllPerson(@AuthenticationPrincipal User user,HttpServletRequest request) {
		logger.info("spring-security-user: {}",JsonUtils.toJson(user));
		List<Map<String, Object>> allPerson = service.queryAllPerson();
		return allPerson;
	}

	@GetMapping("/person/{id}/")
	public Person queryPerson(@PathVariable("id") Long id) {
		Optional<Person> queryPerson = service.queryPerson(id);
		Person p = queryPerson.orElse(null);
		return p;
	}

	@GetMapping("/nation/{id}/")
	public Nation queryNation(@PathVariable("id") Long id) {
		Optional<Nation> queryNation = service.queryNation(id);
		return queryNation.orElse(null);
	}

	@GetMapping("/a/employee/{id}/")
	public Employee queryEmployee(@PathVariable("id") Long id) {
		Optional<Employee> queryEmployee = service.queryEmployee(id);
		return queryEmployee.orElse(null);
	}

	@GetMapping("/b/company/{id}/")
	public Company queryCompany(@PathVariable("id") Long id) {
		Optional<Company> queryCompany = service.queryCompany(id);
		return queryCompany.orElse(null);
	}

	@GetMapping("/a/uvgroup")
	public List<Map<String, Object>> queryUvGroup() {
		return service.queryUvGroup();
	}

	@GetMapping("/b/geo")
	public List<Map<String, Object>> queryGeo() {
		return service.queryGeo();
	}

	@GetMapping("/updateperson/")
	public void updatePerson() {
		service.updatePerson();
	}

	@GetMapping("/updatepersonexception/")
	public void updatePersonException() {
		service.updatePersonException();
	}

	@GetMapping("/b/updatecompany/")
	public void updateCompany() {
		service.updateCompany();
	}

	@GetMapping("/b/updatecompanyexception/")
	public void updateCompanyException() {
		service.updateCompanyException();
	}

	@PostMapping("/person/")
	public ResponseEntity<?> createPerson(@RequestBody Person person) {
		Person created = service.createPerson(person);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/").buildAndExpand(created.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/person/{id}/")
	@PatchMapping("/person/{id}/")
	public ResponseEntity<?> updatePerson(@RequestBody Person person, @PathVariable Long id) {
		service.queryPerson(id).ifPresent(old -> {
			old.setBirthDay(person.getBirthDay());
			old.setName(person.getName());
			service.updatePerson(old);
		});
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri();
		return ResponseEntity.created(location).build();
	}
	
}
