/**
 * 
 */
package org.yanhuang.springcloud.service.rest.util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.junit.Test;
import org.yanhuang.springcloud.rest.jpa.domain.Person;
import org.yanhuang.springcloud.rest.util.JsonUtils;

/**
 * @author zhyhang
 *
 */
public class JsonUtilsTest {

	@Test
	public void testToJson() throws IOException {

		LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(0), ZoneId.systemDefault());
		System.out.println(JsonUtils.toJson(ldt));
		System.out.println(JsonUtils.toJson(Long.valueOf(1024)));

	}

}
