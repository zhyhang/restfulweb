/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.domain.security;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.yanhuang.springcloud.rest.entity.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author zhyhang
 *
 */
@Entity
@Table(name="security_role")
public class Role extends BaseEntity{

	private static final long serialVersionUID = 1338307177014656546L;

	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Column(name="descript")
	private String descript;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "security_role_privilege", inverseJoinColumns = @JoinColumn(name = "privilege_id"), joinColumns = @JoinColumn(name = "role_id"))
	@JsonIgnore
	private Set<Privilege> privileges;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
	
}
