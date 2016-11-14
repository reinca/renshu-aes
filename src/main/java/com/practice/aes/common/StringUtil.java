package com.practice.aes.common;

import java.io.UnsupportedEncodingException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

// Because storage capacity, abanndoned using commons-lang
public class StringUtil {
	
	// String to Hex String
	public static String toHexString(String text) {
		byte[] myBytes;
		try {
			myBytes = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			myBytes = text.getBytes();
		}
		return DatatypeConverter.printHexBinary(myBytes);
	}

	// Hex String to String
	public static String toString(String text) {
		byte[] bytes = DatatypeConverter.parseHexBinary(text);
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			return new String(bytes);
		}
	}
	
	// Random String generator (a-zA-Z0-9)
	public static String sentenceGenerator(long length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
