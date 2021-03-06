/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient.loginType;

/**
 * @author zhyhang
 *
 */
@Component
public class FormLoginSuccessHandler extends LoginSuccessHandler {
	@Autowired
	private LoginClientHanlderForm loginClientHanlder;

	@Override
	protected LoginClientHandler getLoginClientHandler() {
		return loginClientHanlder;
	}

	@Component
	public static class LoginClientHanlderForm extends LoginClientHandler {

		@Override
		protected loginType actionType() {
			return loginType.login_form;
		}

		@Override
		protected void fillLoginClient(Account account, HttpServletRequest request, HttpServletResponse response,
				LoginClient client, Authentication authentication) {
		}

	}

}
