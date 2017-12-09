/**
 * 
 */
package org.yanhuang.springcloud.rest.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.AuditorAware;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;
import org.yanhuang.springcloud.rest.util.SecurityUtils;

/**
 * for entity create by, last modifier and etc.
 * @author zhyhang
 *
 */
@Configuration
public class AuditorAwareDefaultConfiger {

	@Bean
	@Primary
	public AuditorAware<?> auditorProvider() {
		return new SpringSecurityAuditorAware();
	}

}

class SpringSecurityAuditorAware implements AuditorAware<User> {

	public Optional<User> getCurrentAuditor() {
		return Optional.ofNullable(SecurityUtils.getAuthUser());
	}
}
