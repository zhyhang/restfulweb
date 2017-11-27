/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.b.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yanhuang.springcloud.rest.jpa.b.domain.Company;

/**
 * @author zhyhang
 *
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
