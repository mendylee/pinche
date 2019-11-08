package com.jinzhun.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.codec.Hex;

import com.jinzhun.common.exception.UnexpectedException;

public class Codec {
	
	public static final String ALGORITHM_MD5 = "MD5";
	
	public static final String ALGORITHM_SHA1 = "SHA-1";
	
	public static String uuid() { return java.util.UUID.randomUUID().toString().replace("-", "");}
	
	public static String randomString() {return RandomStringUtils.randomAlphanumeric(36);}
	
	public static String encodeBase64(byte[] value) { return new String(Base64.encodeBase64(value)); }
	
	public static String encodeBase64(String value) { return encodeBase64(value.getBytes(Charsets.UTF_8));}
	
	public static byte[] decodeBase64(String value) { return Base64.decodeBase64(value.getBytes(Charsets.UTF_8));}
	
	public static String encodeMd5(String source, String target) { return hexMd5(source + target);}
	
	public static String hexMd5(String value) { 
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_MD5);
			messageDigest.reset();
			messageDigest.update(value.getBytes(Charsets.UTF_8));
			byte[] digest = messageDigest.digest();
			return byte2Hex(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public static String hexSHA(String value) {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_SHA1);
			messageDigest.reset();
			messageDigest.update(value.getBytes(Charsets.UTF_8));
			byte[] digest = messageDigest.digest();
			return byte2Hex(digest);
		} catch (NoSuchAlgorithmException e) {
			throw new UnexpectedException(e);
		}
	}
	
	public static String byte2Hex(byte[] digest) { return String.valueOf(Hex.encode(digest));}
	
	public static byte[] hex2Byte(String hexString) { return Hex.decode(hexString);}

	
}
