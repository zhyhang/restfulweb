/**
 * 
 */
package org.yanhuang.springcloud.rest.util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;

/**
 * @author zhyhang
 *
 */
public class SecurityUtils {

	/**
	 * get current authenticated user, or else null
	 * 
	 * @return
	 */
	public static User getAuthUser() {
		return (User) Optional.of(SecurityContextHolder.getContext()).map(SecurityContext::getAuthentication)
				.map(Authentication::getPrincipal).filter(p -> p instanceof User).orElse(null);
	}

	/**
	 * case login then true, else false
	 * 
	 * @return
	 */
	public static boolean hasLogin() {
		return null != getAuthUser();
	}

}
