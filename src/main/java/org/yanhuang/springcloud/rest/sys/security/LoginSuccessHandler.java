/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient.loginType;
import org.yanhuang.springcloud.rest.jpa.domain.security.User;
import org.yanhuang.springcloud.rest.jpa.repo.security.LoginClientRepository;
import org.yanhuang.springcloud.rest.util.DateTimeUtils;
import org.yanhuang.springcloud.rest.util.ServletUtils;

/**
 * @author zhyhang
 *
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private LoginClientRepository loginClientRepo;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		User user = (User) authentication.getPrincipal();
		LoginClient savedClient = recordLoginClient(user, request, response);
		user.setLoginClient(savedClient);
		super.onAuthenticationSuccess(request, response, authentication);
	}

	private LoginClient recordLoginClient(User user, HttpServletRequest request, HttpServletResponse response) {
		LoginClient client = new LoginClient();
		client.setUserid(user.getId());
		client.setUsername(user.getUsername());
		client.setSession(ServletUtils.getHttpSessionId(request));
		client.setIpv4(ServletUtils.getIpAddr(request));
		client.setActionType(loginType.login);
		client.setUa(StringUtils.substring(Optional.ofNullable(ServletUtils.getUseragent(request)).orElse(""), 0, 512));
		client.setReferUrl(
				StringUtils.substring(Optional.ofNullable(ServletUtils.getRefer(request)).orElse(""), 0, 2048));
		client.setActionTime(DateTimeUtils.current());
		client.setCookie(ServletUtils.createIfAbsentCookie(request, response));
		LoginClient savedClient = loginClientRepo.save(client);
		return savedClient;
	}

}
