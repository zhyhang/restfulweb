/**
 * 
 */
package org.yanhuang.springcloud.rest.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * multi datasource jdbc config</br>
 * <b>When using transaction manager, refer JpaDbAConfiger and JpaDbBConfiger </b>
 * @author zhyhang
 *
 */
@Configuration
public class JdbcTemplateConfiger {
	
	@Bean(name = "jdbcTemplateDefault")
	@Primary
	public JdbcTemplate jdbcTemplateDefault(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "jdbcTemplateA")
	public JdbcTemplate jdbcTemplateA(@Qualifier("datasourceA") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "jdbcTemplateB")
	public JdbcTemplate jdbcTemplateB(@Qualifier("datasourceB") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
