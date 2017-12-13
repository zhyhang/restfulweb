/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author zhyhang
 *
 */
public class Account extends User {

	private static final long serialVersionUID = 1559817647539975627L;

	/**
	 * domain user id
	 */
	private Long userId;

	/**
	 * LoginClient info ID, only hold id for saving memory
	 */
	private Long loginClientId;

	public Account(Long userId, String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.userId = userId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getLoginClientId() {
		return loginClientId;
	}

	public void setLoginClientId(Long loginClientId) {
		this.loginClientId = loginClientId;
	}

}
