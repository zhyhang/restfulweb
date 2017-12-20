/**
 * 
 */
package org.yanhuang.springcloud.rest.service.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.Privilege;
import org.yanhuang.springcloud.rest.jpa.domain.security.Role;
import org.yanhuang.springcloud.rest.jpa.repo.security.RoleRepository;

/**
 * @author zhyhang
 *
 */
@Component
public class RolePrivilegeService {
	@Autowired
	private RoleRepository roleRepo;
	
	public void saveRoleTest() {
		Role role = new Role();
		role.setName("ROLE_1");
		role.setCode(role.getName());
		Set<Privilege> ps=new HashSet<>();
		Privilege p=new Privilege();
		p.setName("p_menu_1");
		p.setCode(role.getName());
		ps.add(p);
		p=new Privilege();
		p.setName("p_menu_2");
		p.setCode(role.getName());
		ps.add(p);
		role.setPrivileges(ps);
		roleRepo.save(role);
	}

}
