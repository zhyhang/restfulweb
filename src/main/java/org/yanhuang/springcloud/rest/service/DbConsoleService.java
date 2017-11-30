/**
 * 
 */
package org.yanhuang.springcloud.rest.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.yanhuang.springcloud.rest.jpa.a.domain.Employee;
import org.yanhuang.springcloud.rest.jpa.a.repo.EmployeeRepository;
import org.yanhuang.springcloud.rest.jpa.b.domain.Company;
import org.yanhuang.springcloud.rest.jpa.b.repo.CompanyRepository;
import org.yanhuang.springcloud.rest.jpa.domain.Person;
import org.yanhuang.springcloud.rest.jpa.domain.o.Nation;
import org.yanhuang.springcloud.rest.jpa.repo.PersonRepository;
import org.yanhuang.springcloud.rest.jpa.repo.o.NationRepository;

/**
 * @author zhyhang
 *
 */
@Component
public class DbConsoleService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("jdbcTemplateA")
	private JdbcTemplate jdbcTemplateA;

	@Autowired
	@Qualifier("jdbcTemplateB")
	private JdbcTemplate jdbcTemplateB;

	@Autowired
	private PersonRepository personRepo;

	@Autowired
	private NationRepository nationRepo;

	@Autowired
	private EmployeeRepository employeeRepo;

	@Autowired
	private CompanyRepository companyRepo;

	public List<Map<String, Object>> queryAllPerson() {
		try {
			return jdbcTemplate.queryForList("select * from person");
		} catch (Exception e) {
			logger.error("queryAllPerson error:", e);
			return Collections.emptyList();
		}
	}

	public Optional<Person> queryPerson(Long id) {
		try {
			return personRepo.findById(id);
		} catch (Exception e) {
			logger.error("queryPerson[{}] error:", id, e);
			return Optional.empty();
		}
	}

	public Optional<Nation> queryNation(Long id) {
		try {
			return nationRepo.findById(id);
		} catch (Exception e) {
			logger.error("queryNation[{}] error:", id, e);
			return Optional.empty();
		}
	}

	public Optional<Employee> queryEmployee(Long id) {
		try {
			return employeeRepo.findById(id);
		} catch (Exception e) {
			logger.error("queryEmployee[{}] error:", id, e);
			return Optional.empty();
		}
	}

	public Optional<Company> queryCompany(Long id) {
		try {
			return companyRepo.findById(id);
		} catch (Exception e) {
			logger.error("queryCompany[{}] error:", id, e);
			return Optional.empty();
		}
	}

	public List<Map<String, Object>> queryUvGroup() {
		try {
			return jdbcTemplateA.queryForList("select * from uv_tag_group limit 10");
		} catch (Exception e) {
			logger.error("queryUv error:", e);
			return Collections.emptyList();
		}
	}

	public List<Map<String, Object>> queryGeo() {
		try {
			return jdbcTemplateB.queryForList("select * from geo limit 10");
		} catch (Exception e) {
			logger.error("queryGeo error:", e);
			return Collections.emptyList();
		}
	}

	public void updateCompany() {
		jdbcTemplateB.update("update company set last_modified=now() where id=1");
	}

	@Transactional(transactionManager = "transactionManagerB")
	public void updateCompanyException() {
		jdbcTemplateB.update("update company set last_modified='2017-12-12 12:12:12' where id=1");
		throw new RuntimeException("update company exception");
	}

	public void updatePerson() {
		jdbcTemplate.update("update person set last_modified=now() where id=1");
	}

	@Transactional
	public void updatePersonException() {
		jdbcTemplate.update("update person set last_modified='2017-12-12 12:12:12' where id=1");
		throw new RuntimeException("update person exception");
	}

	@Transactional
	public Person createPerson(Person person) {
			return personRepo.save(person);
	}
	
	@Transactional
	public void updatePerson(Person person) {
			personRepo.save(person);
	}
}
