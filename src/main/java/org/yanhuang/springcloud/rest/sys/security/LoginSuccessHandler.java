/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;

/**
 * @author zhyhang
 *
 */
public abstract class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		Account account = (Account) authentication.getPrincipal();
		LoginClient savedClient = getLoginClientHandler().createSaveLoginClient(account, request, response,
				authentication);
		account.setLoginClientId(savedClient.getId());
		super.onAuthenticationSuccess(request, response, authentication);
	}

	protected abstract LoginClientHandler getLoginClientHandler();
}
