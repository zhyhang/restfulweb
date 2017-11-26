/**
 * 
 */
package org.yanhuang.springcloud.rest.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * multi datasource support config</br>
 * <b>When startup this app error, see(spring-boot 2.0.0.M6):</b></br>
 * https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html</br>
 * @author zhyhang
 *
 */
@Configuration
public class DatasourceConfiger {
	
	
	@Bean("datasourceDefaultProp")
	@ConfigurationProperties(prefix="spring.datasource")
	@Primary
	public DataSourceProperties datasourceDefaultProp() {
	    return new DataSourceProperties();
	}
	
	@Bean(name = "datasourceDefault")
	@ConfigurationProperties(prefix="spring.datasource")
	@Primary
    public DataSource datasourceDefault() {
        return datasourceDefaultProp().initializeDataSourceBuilder().build();
    }
	
	@Bean("mariadbDsAProp")
	@ConfigurationProperties(prefix="spring.datasource.mariadb.a")
	public DataSourceProperties mariadbDsAProp() {
	    return new DataSourceProperties();
	}

	@Bean(name = "mariadbDsA")
	@ConfigurationProperties(prefix="spring.datasource.mariadb.a")
    public DataSource mariadbDsA() {
        return mariadbDsAProp().initializeDataSourceBuilder().build();
    }
	
	@Bean("mariadbDsBProp")
	@ConfigurationProperties(prefix="spring.datasource.mariadb.b")
	public DataSourceProperties mariadbDsBProp() {
	    return new DataSourceProperties();
	}

    @Bean(name = "mariadbDsB")
    @ConfigurationProperties(prefix="spring.datasource.mariadb.b")
    public DataSource mariadbDsB() {
        return mariadbDsBProp().initializeDataSourceBuilder().build();
    }

}
