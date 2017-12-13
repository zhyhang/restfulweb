package org.yanhuang.springcloud.rest.util;

import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * service instance id utils
 * 
 * @author zhyhang
 *
 */
public class ServiceIds {

	private static final Pattern ID_PATTERN = Pattern.compile("[0-9A-Za-z]{3}");

	private static final String iid;

	static {
		String id = System.getProperty("service_instance_id");
		if (!validId(id)) {
			try {
				InputStream stream = ServiceIds.class.getResourceAsStream("/serviceid.properties");
				Properties pps = new Properties();
				pps.load(stream);
				id = pps.getProperty("service_instance_id");
			} catch (Exception e) {
			}
		}
		if (!validId(id)) {
			throw new RuntimeException(
					"not found service id! please set service_instance_id = XXX (is and only is 3 digit or alpha), in system property or /serviceid.properties file.");
		}
		iid = id;
	}

	private static boolean validId(String id) {
		return id != null && ID_PATTERN.matcher(id).matches();
	}

	/**
	 * this service instance id, i.e. service running instance identifier
	 * 
	 * @return
	 */
	public static String id() {
		return iid;
	}

}
