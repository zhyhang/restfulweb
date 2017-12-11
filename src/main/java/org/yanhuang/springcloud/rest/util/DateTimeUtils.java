/**
 * 
 */
package org.yanhuang.springcloud.rest.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author zhyhang
 *
 */
public class DateTimeUtils {
	

	public static LocalDateTime current() {
		return LocalDateTime.now();
	}
	
	public static LocalDateTime parseMillis(long millis) {
		return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
}
