/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.repo.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;

/**
 * @author zhyhang
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByUsername(String username);

}
