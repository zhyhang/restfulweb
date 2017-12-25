/**
 * 
 */
package org.yanhuang.springcloud.rest.service.security;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.yanhuang.springcloud.rest.jpa.domain.security.Privilege;
import org.yanhuang.springcloud.rest.jpa.domain.security.Role;
import org.yanhuang.springcloud.rest.jpa.repo.security.PrivilegeRepository;
import org.yanhuang.springcloud.rest.jpa.repo.security.RoleRepository;

/**
 * @author zhyhang
 *
 */
@Component
public class RolePrivilegeService {
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PrivilegeRepository privilegeRepo;
	
	@Transactional
	public void saveRoleTest() {
		Role role = new Role();
		role.setName("ROLE_1");
		role.setCode(role.getName());
		Set<Role> rs = new HashSet<>();
		rs.add(role);
		Set<Privilege> ps=new HashSet<>();
		Privilege p=new Privilege();
		p.setName("p_menu_1");
		p.setCode(p.getName());
		p.setRoles(rs);
		ps.add(privilegeRepo.save(p));
		p=new Privilege();
		p.setName("p_menu_2");
		p.setCode(p.getName());
		p.setRoles(rs);
		ps.add(privilegeRepo.save(p));
		role.setPrivileges(ps);
		roleRepo.save(role);
		
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		role.setName(role.getName()+"_update_from_privilege");
//		role.getPrivileges().iterator().next().setName("p_menu_u");
		
//		roleRepo.save(role);
//		privilegeRepo.save(role.getPrivileges().iterator().next());
		
//		role=roleRepo.findById(2L).get();
		
		Privilege pr=privilegeRepo.findById(4L).orElse(null);
		
		LoggerFactory.getLogger(this.getClass()).info("privilege-role:{}",pr.getRoles());
		
	}

}
