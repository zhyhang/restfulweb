/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient.loginType;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;

/**
 * @author zhyhang
 *
 */
@Component
public class LogoutHandler implements org.springframework.security.web.authentication.logout.LogoutHandler {

	@Autowired
	private LoginClientHanlderForm loginClientHanlder;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Object principal = Optional.ofNullable(authentication).map(Authentication::getPrincipal).orElse(null);
		if (principal instanceof User) {
			User user = (User) principal;
			LoginClient savedClient = loginClientHanlder.createSaveLoginClient(user, request, response, authentication);
			user.setLoginClient(savedClient);
		}
	}

	@Component
	public static class LoginClientHanlderForm extends LoginClientHandler {

		@Override
		protected loginType actionType() {
			return loginType.logout;
		}

		@Override
		protected void fillLoginClient(User user, HttpServletRequest request, HttpServletResponse response,
				LoginClient client, Authentication authentication) {
		}

	}

}
