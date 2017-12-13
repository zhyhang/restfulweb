/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient.loginType;
import org.yanhuang.springcloud.rest.jpa.repo.security.LoginClientRepository;
import org.yanhuang.springcloud.rest.util.DateTimeUtils;
import org.yanhuang.springcloud.rest.util.ServiceIds;
import org.yanhuang.springcloud.rest.util.ServletUtils;

/**
 * @author zhyhang
 *
 */
public abstract class LoginClientHandler {

	@Autowired
	private LoginClientRepository loginClientRepo;

	public LoginClient createSaveLoginClient(Account account, HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		LoginClient client = new LoginClient();
		client.setUserid(account.getUserId());
		client.setUsername(account.getUsername());
		client.setSession(ServletUtils.getHttpSessionId(request));
		client.setSessionTimeoutSec(ServletUtils.getHttpSession(request).map(s->s.getMaxInactiveInterval()).orElse(null));
		client.setLastAccess(ServletUtils.getHttpSession(request).map(HttpSession::getLastAccessedTime)
				.map(DateTimeUtils::parseMillis).orElse(null));
		client.setIpv4(ServletUtils.getIpAddr(request));
		client.setActionType(actionType());
		client.setUa(StringUtils.substring(Optional.ofNullable(ServletUtils.getUseragent(request)).orElse(""), 0, 512));
		client.setReferUrl(
				StringUtils.substring(Optional.ofNullable(ServletUtils.getRefer(request)).orElse(""), 0, 2048));
		client.setActionTime(DateTimeUtils.current());
		client.setCookie(ServletUtils.createIfAbsentCookie(request, response));
		client.setServiceId(ServiceIds.id());
		fillLoginClient(account, request, response, client, authentication);
		LoginClient savedClient = loginClientRepo.save(client);
		return savedClient;
	}

	protected abstract loginType actionType();

	protected abstract void fillLoginClient(Account account, HttpServletRequest request, HttpServletResponse response,
			LoginClient client, Authentication authentication);

}
