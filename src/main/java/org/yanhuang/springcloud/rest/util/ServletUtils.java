/**
 * 
 */
package org.yanhuang.springcloud.rest.util;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhyhang
 *
 */
public class ServletUtils {

	private static final String IPADRESS_TYPE_UNKNOWN = "unknown";

	private static final String COOKIE_DOMAIN_DEFAULT = "yanghuangshi.org";

	private static final String COOKIE_KEY_DEFAULT = "YHSID";

	public static String getIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("X-Real-IP");

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains(IPADRESS_TYPE_UNKNOWN)) {
			ip = request.getHeader("x-forwarded-for");
		} else if (ip.contains(",")) {
			String[] ips = StringUtils.split(ip, ',');
			if (ips.length > 0) {
				return ips[ips.length - 1];
			}
		} else {
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains(IPADRESS_TYPE_UNKNOWN)) {
			ip = request.getHeader("Proxy-Client-IP");
		} else {
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains(IPADRESS_TYPE_UNKNOWN)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		} else {
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains(IPADRESS_TYPE_UNKNOWN)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		} else {
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains(IPADRESS_TYPE_UNKNOWN)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		} else {
			return ip;
		}

		if (StringUtils.isBlank(ip) || ip.toLowerCase().contains(IPADRESS_TYPE_UNKNOWN)) {
			return request.getRemoteAddr();
		} else {
			return ip;
		}
	}

	public static String getHttpSessionId(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		HttpSession session = request.getSession(false);
		if (session != null) {
			return session.getId();
		}
		return null;
	}

	public static String getUseragent(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		return request.getHeader("User-Agent");
	}

	public static String getRefer(HttpServletRequest request) {
		if (request == null) {
			return null;
		}
		return request.getHeader("Referer");
	}
	
	public static String getCookie(HttpServletRequest request, String name) {
		Optional<Cookie[]> cs = Optional.ofNullable(request).map(r -> r.getCookies());
		if (cs.isPresent()) {
			for (Cookie c : cs.get()) {
				if (c != null && name != null && name.equals(c.getName())) {
					return c.getValue();
				}
			}
		}
		return null;
	}

	public static void setCookie(String domain, String key, String value, int expiry, HttpServletResponse response) {
		response.setHeader("P3P",
				"CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(expiry);
		cookie.setDomain(domain);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void setCookieDefault(HttpServletResponse response, String value) {
		setCookie(COOKIE_DOMAIN_DEFAULT, COOKIE_KEY_DEFAULT, value, (int) TimeUnit.DAYS.toSeconds(365 * 3), response);
	}

	public static String getCookie(String key, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (key.equals(cookies[i].getName())) {
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}

	public static String getCookieDefault(HttpServletRequest request) {
		return getCookie(COOKIE_KEY_DEFAULT, request);
	}

	private static final AtomicInteger cookieCounter = new AtomicInteger(0);
	private static final String startSign = alignTo(Integer.toHexString(ThreadLocalRandom.current().nextInt(1024)), 3);

	public static String generateCookie() {
		String millis = alignTo(Integer.toHexString((int) System.currentTimeMillis()), 8);
		String counters = alignTo(Integer.toHexString(cookieCounter.incrementAndGet()), 8);
		return startSign + millis + counters;
	}

	private static String alignTo(String orig, int len) {
		int diff = len - orig.length();
		if (diff > 0) {
			char[] newChars = new char[len];
			Arrays.fill(newChars, '0');
			System.arraycopy(orig.toCharArray(), 0, newChars, diff, orig.length());
			return new String(newChars);
		}
		return orig;
	}
	
	public static String createIfAbsentCookie(HttpServletRequest request, HttpServletResponse response) {
		String cookie=getCookieDefault(request);
		if(StringUtils.isBlank(cookie)) {
			cookie=generateCookie();
		}
		setCookieDefault(response, cookie);
		return cookie;
	}
	
}
