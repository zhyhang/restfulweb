/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient.loginType;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;

/**
 * @author zhyhang
 *
 */
@Component
public class LogoutHandler extends SimpleUrlLogoutSuccessHandler {

	@Autowired
	private LoginClientHanlderForm loginClientHanlder;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (authentication != null && authentication.getPrincipal() != null
				&& authentication.getPrincipal() instanceof User) {
			User user = (User) authentication.getPrincipal();
			loginClientHanlder.createSaveLoginClient(user, request, response);
		}
		super.onLogoutSuccess(request, response, authentication);
	}

	@Component
	public static class LoginClientHanlderForm extends LoginClientHandler {

		@Override
		protected loginType actionType() {
			return loginType.logout;
		}

		@Override
		protected void fillLoginClient(User user, HttpServletRequest request, HttpServletResponse response, LoginClient client) {
			if(user.getLoginClient()!=null) {
				client.setSession(user.getLoginClient().getSession());
			}
		}

	}
}
