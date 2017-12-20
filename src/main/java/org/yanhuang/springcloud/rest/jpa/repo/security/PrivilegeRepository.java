/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.repo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.domain.security.Privilege;

/**
 * @author zhyhang
 *
 */
public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {
	
}
