/**
 * 
 */
package org.yanhuang.springcloud.rest.jpa.domain.security;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.yanhuang.springcloud.rest.entity.BaseEntity;

/**
 * @author zhyhang
 *
 */
@Entity
@Table(name = "security_user")
public class User extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = 4355046442165677931L;

	/**
	 * account, current using email
	 */
	@Column(name = "username")
	@Email
	private String username;

	@Column(name = "password")
	@NotEmpty
	private String password;

	/**
	 * user's nature name
	 */
	@Column(name = "name")
	private String name;

	/**
	 * user's code (e.g. nickname)
	 */
	@Column(name = "code")
	private String code;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	@Size(min = 6, max = 30)
	private String phone;

	@Column(name = "locked")
	private boolean locked = false;

	@Column(name = "expired")
	private boolean expired = false;

	@Column(name = "pass_expired")
	private boolean passExpired = false;

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptySet();
	}

	@Override
	public boolean isAccountNonExpired() {
		return !isExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return !isLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return !isPassExpired();
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public boolean isPassExpired() {
		return passExpired;
	}

	public void setPassExpired(boolean passExpired) {
		this.passExpired = passExpired;
	}

	public static void main(String[] args) {
		System.out.println(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("88888888"));
	}
}
