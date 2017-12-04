/**
 * 
 */
package org.yanhuang.springcloud.service.rest.security;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author zhyhang
 *
 */
public class PasswordEncoderTest {

	@Test
	public void testBcryptTimecost() {
		// strength 10 (default)
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		long tsb = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			encoder.encode("This is a password.");
		}
		System.out.printf("encode 10 times with default strength, take %d ms.\n",
				TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - tsb));

		// strength 8
		encoder = new BCryptPasswordEncoder(8);
		tsb = System.nanoTime();
		for (int i = 0; i < 10; i++) {
			encoder.encode("This is a password.");
		}
		System.out.printf("encode 10 times with strength 8, take %d ms.\n",
				TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - tsb));
	}

}
