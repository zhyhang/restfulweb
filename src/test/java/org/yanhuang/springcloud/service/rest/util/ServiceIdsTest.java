/**
 * 
 */
package org.yanhuang.springcloud.service.rest.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.yanhuang.springcloud.rest.util.ServiceIds;

/**
 * @author zhyhang
 *
 */
public class ServiceIdsTest {

	@Test
	public void testGetIdException() {
		// exception
		try {
			ServiceIds.id();
		}catch(Throwable t) {
			t.printStackTrace();
			return;
		}
		fail();
	}

	@Test
	public void testGetIdInvalidId1() {
		System.setProperty("service_instance_id", "");
		try {
			ServiceIds.id();
		}catch(Throwable t) {
			t.printStackTrace();
			return;
		}
		fail();
	}

	@Test
	public void testGetIdInvalidId2() {
		System.setProperty("service_instance_id", "_12");
		try {
			ServiceIds.id();
		}catch(Throwable t) {
			t.printStackTrace();
			return;
		}
		fail();
	}

	@Test
	public void testGetIdInvalidId3() {
		System.setProperty("service_instance_id", "ab");
		try {
			ServiceIds.id();
		}catch(Throwable t) {
			t.printStackTrace();
			return;
		}
		fail();
	}

	@Test
	public void testGetIdvalidId1() {
		System.setProperty("service_instance_id", "ab1");
		assertEquals("ab1", ServiceIds.id());
	}

}
