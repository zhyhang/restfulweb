/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.repo.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;

/**
 * @author zhyhang
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.username=?1 and u.removed=false")
	Optional<User> findByUsername(String username);

}
