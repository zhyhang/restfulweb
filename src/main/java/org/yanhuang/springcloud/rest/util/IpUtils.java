package org.yanhuang.springcloud.rest.util;

/**
 * ip convert tools
 * @author zhyhang
 * TODO license
 *
 */
public class IpUtils {

	/**
	 * throw exception when invalid ip
	 * @param ip
	 * @return
	 */
	public static byte[] ip2Bytes(String ip) {
		if (ip == null) {
			return null;
		}
		byte[] ret = new byte[] { 0, 0, 0, 0 };
		int index = 0;
		for (int i = 0; i < ip.length(); i++) {
			char c = ip.charAt(i);
			if (c == '.') {
				index++;
				continue;
			}
			if (c > '9' || c < '0' || ret[index] < 0 || ret[index] >= 100) {
				throw new RuntimeException("Invalid ip:" + ip);
			}
			ret[index] = (byte) (ret[index] * 10 + (c - 48));
		}
		return ret;
	}

	public static String bytes2Ip(byte[] bytes) {
		if (bytes == null) {
			return null;
		}
		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			if (bytes.length > i) {
				ret.append(0xff & bytes[i]).append(".");
			} else {
				ret.append(0).append(".");
			}
		}
		if (bytes.length > 3) {
			ret.append(0xff & bytes[3]);
		} else {
			ret.append(0);
		}
		return ret.toString();
	}

	/**
	 * throw exception when invalid ip
	 * @param ip
	 * @return
	 */
	public static int ip2Int(String ip) {
		byte[] bytes = ip2Bytes(ip);
		return (0xff000000 & bytes[0] << 24) | (0x00ff0000 & bytes[1] << 16) | (0x0000ff00 & bytes[2] << 8)
				| (0x000000ff & bytes[3]);
	}

	/**
	 * throw exception when invalid ip
	 * @param ip
	 * @return
	 */
	public static long ip2Long(String ip) {
		byte[] bytes = ip2Bytes(ip);
		return (0xff000000L & bytes[0] << 24) | (0x00ff0000L & bytes[1] << 16) | (0x0000ff00L & bytes[2] << 8)
				| (0x000000ffL & bytes[3]);
	}

	/**
	 * throw exception when invalid int
	 * @param i
	 * @return
	 */
	public static String int2Ip(int i) {
		byte[] bytes = new byte[4];
		bytes[0] = (byte) (i >> 24);
		bytes[1] = (byte) (i >> 16);
		bytes[2] = (byte) (i >> 8);
		bytes[3] = (byte) i;
		return bytes2Ip(bytes);
	}

	/**
	 * throw exception when invalid i
	 * @param i
	 * @return
	 */
	public static String long2Ip(long i) {
		return int2Ip((int) i);
	}

	public static boolean isPrivateIp(long ip) {
		// scope of private ip
		// 10.0.0.0~10.255.255.255(167772160-184549375)
		// 127.0.0.0~127.255.255.255(2130706432-2147483647)
		// 172.16.0.0~172.31.255.255(2886729728-2887778303)
		// 169.254.0.0~169.254.255.255(2851995648-2852061183)
		// 192.168.0.0~192.168.255.255(3232235520-3232301055)
		return ip >= 167772160L && ip <= 184549375L || ip >= 2130706432L && ip <= 2147483647L || ip >= 2886729728L
				&& ip <= 2887778303L || ip >= 2851995648L && ip <= 2852061183L || ip >= 3232235520L
				&& ip <= 3232301055L;
	}

	/**
	 * throw exception when invalid ip
	 * @param ip
	 * @return
	 */
	public static boolean isPrivateIp(String ip) {
		return isPrivateIp(ip2Long(ip));
	}

	public static boolean isPrivateIp(int ip) {
		return isPrivateIp(0xffffffffL & ip);
	}

}
