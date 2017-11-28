/**
 * 
 */
package org.yanhuang.springcloud.rest.util;

import java.io.IOException;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhyhang
 *
 */
public class JsonUtils {

	private static final ObjectMapper om = Jackson2ObjectMapperBuilder.json().build();

	public static String toJson(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return om.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromJson(String json, Class<T> cls) {
		if (json == null || json.trim().isEmpty()) {
			return null;
		}
		try {
			return om.readValue(json, cls);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static JsonNode readTree(String jsonStr) {
		if (jsonStr == null) {
			return null;
		}
		try {
			return om.readTree(jsonStr);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] toBytes(Object obj) {
		try {
			return obj != null ? om.writeValueAsBytes(obj) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T fromBytes(byte[] bs, Class<T> clazz) {
		try {
			return bs != null ? om.readValue(bs, clazz) : null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
