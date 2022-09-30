package com.dragon.server.util;

import java.nio.ByteBuffer;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class NumberUtil {

	private static final String ALL_NUMBER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
	private static final String ALL_FIGURES= "0123456789";
	private static final int RADIX = ALL_NUMBER.length();
	private static final int FIGURE_RADIX = ALL_FIGURES.length();

	public static String generateMsgID() {
		return generateMsgID(2);
	}

	public static String generateMsgID(int randomCount) {
		String result = compressNum(System.currentTimeMillis());
		Random r = new Random();
		StringBuilder buf = new StringBuilder(result);
		for (int i = 0; i < randomCount; i++) {
			buf.append(ALL_NUMBER.charAt(r.nextInt(RADIX)));
		}
		return buf.toString();
	}

	/**
	 * 生成可变长度的 63 进制随机串
	 */
	public static String generateNonce(int length) {
		return generateNonce(length, null);
	}

	public static String generateNonce(int length, Long seed) {
		Random r = new Random();
		if (seed != null) {
			r.setSeed(seed + System.nanoTime());
		}
		char[] cbuf = new char[length];
		for (int i = 0; i < length; i++) {
			cbuf[i] = ALL_NUMBER.charAt(r.nextInt(RADIX));
		}
		return new String(cbuf);
	}

	/**
	 * 生成可变长度的随机数字串
	 */
	public static String generateRandomFigure(int length) {
		return generateRandomFigure(length, null);
	}

	public static String generateRandomFigure(int length, Long seed) {
		Random r = new Random();
		if (seed != null) {
			r.setSeed(seed + System.nanoTime());
		}
		char[] cbuf = new char[length];
		for (int i = 0; i < length; i++) {
			cbuf[i] = ALL_FIGURES.charAt(r.nextInt(FIGURE_RADIX));
		}
		return new String(cbuf);
	}

	public static String compressNum(long m) {
		String result = "";
		while (m >= RADIX) {
			int n = (int) (m % RADIX);
			m = m / RADIX;
			result = ALL_NUMBER.charAt(n) + result;
		}
		result = ALL_NUMBER.charAt((int) m) + result;
		return result;
	}

	public static long bytesToLong(byte[] bytes) {
		if (bytes.length != 8)
			throw new IllegalArgumentException("Long must be 8 bytes");

		return ByteBuffer.wrap(bytes).getLong();
	}

	public static byte[] longToBytes(long value) {
		return ByteBuffer.allocate(8).putLong(value).array();
	}

	public static int bytesToInt(byte[] bytes) {
		if (bytes.length != 4)
			throw new IllegalArgumentException("Int must be 4 bytes");

		return ByteBuffer.wrap(bytes).getInt();
	}

	public static byte[] intToBytes(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	public static long uintToLong(int i) {
		if (i < 0) {
			long mm = (long) Integer.MAX_VALUE + 1;
			return (mm << 1) + i;
		}
		return i;
	}

	public static Short getShort(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Short.parseShort(s.trim());
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static Integer getInteger(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Integer.parseInt(s.trim());
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static Long getLong(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Long.parseLong(s.trim());
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	public static Double getDouble(String s) {
		if (s != null && s.length() > 0) {
			try {
				return Double.parseDouble(s.trim());
			} catch (NumberFormatException e) {
			}
		}
		return null;
	}

	/**
	 * 计算两个 long 型数之和，考虑溢出
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static long saturatedAdd(long a, long b) {
	    long naiveSum = a + b;
	    if ((a ^ b) < 0 | (a ^ naiveSum) >= 0) {
	      // If a and b have different signs or a has the same sign as the result then there was no
	      // overflow, return.
	      return naiveSum;
	    }
	    // we did over/under flow, if the sign is negative we should return MAX otherwise MIN
	    return Long.MAX_VALUE + ((naiveSum >>> (Long.SIZE - 1)) ^ 1);
	}

    /**
     * 进行随机，返回是否命中
     *
     * @param prob 命中概率（0～100）
     * @return 是否命中
     */
    public static boolean randHitProb(int prob) {
        if (prob <= 0) {
            return false;
        }
        if (prob >= 100) {
            return true;
        }
        Random rand = new Random();
        return prob > rand.nextInt(100);
    }

    public static Integer decInteger(String hexValueStr) {
        hexValueStr = StringUtils.trimToNull(hexValueStr);
        if (hexValueStr == null) {
            return null;
        }
        try {
            return Integer.parseInt(hexValueStr, 16);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
