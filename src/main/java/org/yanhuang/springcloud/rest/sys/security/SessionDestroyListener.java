/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import java.util.Collections;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient;
import org.yanhuang.springcloud.rest.jpa.domain.security.LoginClient.loginType;
import org.yanhuang.springcloud.rest.jpa.repo.security.LoginClientRepository;
import org.yanhuang.springcloud.rest.util.DateTimeUtils;
import org.yanhuang.springcloud.rest.util.ServiceIds;

/**
 * @author zhyhang
 *
 */
@Component
public class SessionDestroyListener {

	@Autowired
	private LoginClientRepository loginClientRepo;

	@EventListener
	public void listenerSessionDestroy(SessionDestroyedEvent event) {
		Optional.ofNullable(event.getSecurityContexts()).orElse(Collections.emptyList()).stream().forEach(c -> {
			Account account = (Account) Optional.ofNullable(c).map(sc -> sc.getAuthentication())
					.map(a -> a.getPrincipal()).orElse(null);
			LoginClient client = null;
			if (account != null && account.getLoginClientId() != null
					&& (client = loginClientRepo.findById(account.getLoginClientId()).orElse(null)) != null) {
				try {
					HttpSession s = (HttpSession) event.getSource();
					client.setVersion(-1);
					client.setId(null);
					client.setActionTime(DateTimeUtils.parseMillis(System.currentTimeMillis()));
					client.setActionType(loginType.session_expire);
					client.setSession(event.getId());
					client.setSessionTimeoutSec(s.getMaxInactiveInterval());
					client.setLastAccess(DateTimeUtils.parseMillis(s.getLastAccessedTime()));
					client.setServiceId(ServiceIds.id());
					loginClientRepo.save(client);
				} catch (Exception e) {
					LoggerFactory.getLogger(this.getClass()).error("session-destroy-error", e);
				}
			}
		});
	}

}
