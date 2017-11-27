/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.a.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.a.domain.Employee;

/**
 * @author zhyhang
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
