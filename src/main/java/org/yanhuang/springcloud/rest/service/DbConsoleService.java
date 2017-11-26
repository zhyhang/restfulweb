/**
 * 
 */
package org.yanhuang.springcloud.rest.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

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

	public List<Map<String, Object>> queryAllPerson() {
		try {
			return jdbcTemplate.queryForList("select * from person");
		} catch (Exception e) {
			logger.error("queryAllPerson error:", e);
			return Collections.emptyList();
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
}
