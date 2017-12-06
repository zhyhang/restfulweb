/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.repo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;

/**
 * @author zhyhang
 *
 */
public interface LoginClientRepository extends JpaRepository<LoginClient, Long> {
	
}
