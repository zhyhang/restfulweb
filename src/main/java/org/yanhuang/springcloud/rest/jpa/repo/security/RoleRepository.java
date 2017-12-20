/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.repo.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.domain.security.Role;

/**
 * @author zhyhang
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
	
}
