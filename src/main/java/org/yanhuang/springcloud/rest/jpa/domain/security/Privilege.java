package org.yanhuang.springcloud.rest.jpa.domain.security;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.yanhuang.springcloud.rest.entity.BaseEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="security_privilege")
public class Privilege extends BaseEntity{
	
	private static final long serialVersionUID = 7609662975193297313L;

	public enum privilegeType{
		content,
		menu,
		button;
	}
	
	@Column(name="name")
	private String name;
	
	/**
	 * start with p_ add privilege type add _, e.g. p_menu_mediainsight
	 */
	@Column(name="code")
	private String code;
	
	@Column(name="desc")
	private String desc;
	
	@Column(name="type")
	private privilegeType type;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,mappedBy="privileges")
	@JsonIgnore
	private Set<Role> roles;
}
