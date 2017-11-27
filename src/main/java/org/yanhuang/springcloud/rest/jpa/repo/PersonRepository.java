/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.domain.Person;

/**
 * @author zhyhang
 *
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

}
