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
import org.yanhuang.springcloud.rest.jpa.domain.security.User;
import org.yanhuang.springcloud.rest.jpa.repo.security.LoginClientRepository;
import org.yanhuang.springcloud.rest.util.DateTimeUtils;

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
			User u = (User) Optional.ofNullable(c).map(sc -> sc.getAuthentication()).map(a -> a.getPrincipal())
					.orElse(null);
			LoginClient client = null;
			if (u != null && (client = u.getLoginClient()) != null) {
				try {
					LoginClient cloneClient = client.clone();
					cloneClient.setVersion(-1);
					cloneClient.setId(null);
					cloneClient.setActionTime(DateTimeUtils.parseMillis(System.currentTimeMillis()));
					cloneClient.setActionType(loginType.session_expire);
					cloneClient.setSession(event.getId());
					cloneClient.setLastAccess(
							DateTimeUtils.parseMillis(((HttpSession) event.getSource()).getLastAccessedTime()));
					loginClientRepo.save(cloneClient);
				} catch (Exception e) {
					LoggerFactory.getLogger(this.getClass()).error("session-destroy-error", e);
				}
			}
			LoggerFactory.getLogger(this.getClass()).error("session-destroy: id[{}]", event.getId());
		});
	}

}
