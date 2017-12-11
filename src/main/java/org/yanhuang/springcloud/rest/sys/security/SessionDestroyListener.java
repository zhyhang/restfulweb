/**
 * 
 */
package org.yanhuang.springcloud.rest.sys.security;

import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Component;

/**
 * @author zhyhang
 *
 */
@Component
public class SessionDestroyListener {
	
	@EventListener
	public void listenerSessionDestroy(SessionDestroyedEvent event) {
		LoggerFactory.getLogger(this.getClass()).error("session-destroy: {}",event);
	}

}
