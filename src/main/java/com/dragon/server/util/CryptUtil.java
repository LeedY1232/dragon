package com.dragon.server.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 摘要算法
 */
public class CryptUtil {
	private static Logger logger = LoggerFactory.getLogger(CryptUtil.class);

	private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	private static String digest(String s, String algorithm) {
		if (s == null || s.length() <= 0)
			return s;

		try {
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance(algorithm);
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

    public static String sha1(String s) {
        return digest(s, "SHA1");
    }

    public static String md5(String s) {
        return digest(s, "MD5");
    }

    /**
     * HmacSHA256 加密
     */
    private static byte[] hmac(String encryptText, String encryptKey, String algorithm) {
        try {
            byte[] data=encryptKey.getBytes();
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, algorithm);
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(algorithm);
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);

            byte[] text = encryptText.getBytes();
            //完成 Mac 操作
            return mac.doFinal(text);

        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] hmacSHA256(String data, String key) {
        return hmac(data, key, "HmacSHA256");
    }

	public static byte[] hmacSHA1(String data, String key) {
		return hmac(data, key, "HmacSHA1");
	}

    /**
     * 计算文件MD5
     *
     * @param f
     * @return
     * @throws NoSuchAlgorithmException
     * @throws Exception
     */
    public static String md5(File f) {
        FileInputStream fis = null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(f);
            IOUtils.copy(fis, bout);

            return md5(bout.toByteArray());
        } catch (IOException e) {
            logger.error("md5 error!", e);
            return null;
        } finally {
            IOUtils.closeQuietly(bout);
            IOUtils.closeQuietly(fis);
        }
    }

    public static String md5(byte[] content) {
        if (content == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(content, 0, content.length);
            byte[] bs = md.digest();
            int length = bs.length;
            char[] cs = new char[length * 2];
            int k = 0;
            for (int i = 0; i < length; i++) {
                byte byte0 = bs[i];
                cs[k++] = hexDigits[byte0 >>> 4 & 0xf];
                cs[k++] = hexDigits[byte0 & 0xf];
            }

            return new String(cs);
        } catch (NoSuchAlgorithmException e) {
            logger.error("md5 error!", e);
            return null;
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String b = Integer.toHexString(bytes[i] & 0xFF);
            if (b.length() == 1) {
                hex.append('0');
            }
            hex.append(b);
        }
        return hex.toString();
    }

    public static String md5AndCompress(String src) {
        String randomSalt = String.valueOf(Math.random());
        String s = md5(src + randomSalt);
        String s1 = s.substring(1, 16);
        String s2 = s.substring(17);
        try {
            String compress1 = NumberUtil.compressNum(Long.parseLong(s1, 16));
            String compress2 = NumberUtil.compressNum(Long.parseLong(s2, 16));
            return new StringBuilder(compress1).append(compress2).toString();
        } catch (NumberFormatException e) {
            logger.warn("fail to compress md5 src", e);
            return s;
        }
    }

    /**
     * AES/CBC/PKCS5Padding 加密
     */
    public static String encryptAesCbcPkcs5(String key, String iv, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);

        byte[] dataBytes = data.getBytes("utf-8");
        byte[] encrypted = cipher.doFinal(dataBytes);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * AES/CBC/PKCS5Padding 解密
     */
    public static String decryptAesCbcPkcs5(String key, String iv, String data) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

        byte[] encrypted1 = Base64.getDecoder().decode(data);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, "utf-8");
    }

    /**
     * AES/ECB/PKCS5Padding 加密
     */
    public static String encryptAesEcbPkcs5(String data, String encryptKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        byte[] dataBytes = data.getBytes("utf-8");
        byte[] encrypted = cipher.doFinal(dataBytes);
        return Base64.getEncoder().encodeToString(encrypted);
    }

    /**
     * AES/ECB/PKCS5Padding 解密
     */
    public static String decryptAesEcbPkcs5(String data, String decryptKey) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] encrypted1 = Base64.getDecoder().decode(data);
        byte[] decryptBytes = cipher.doFinal(encrypted1);
        return new String(decryptBytes, "utf-8");
    }

}
