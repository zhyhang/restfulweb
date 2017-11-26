/**
 * 
 */
package org.yanhuang.springcloud.rest.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yanhuang.springcloud.rest.service.DbConsoleService;

/**
 * database console 
 * @author zhyhang
 *
 */
@RestController
public class DbConsoleController {
	
	@Autowired
	private DbConsoleService service;
	
	@GetMapping("/allperson")
	public List<Map<String, Object>> queryAllPerson() {
		return service.queryAllPerson();
	}

	@GetMapping("/uvgroup")
	public List<Map<String, Object>> queryUvGroup() {
		return service.queryUvGroup();
	}
	
	@GetMapping("/geo")
	public List<Map<String,Object>> queryGeo() {
		return service.queryGeo();
	}
	
}
