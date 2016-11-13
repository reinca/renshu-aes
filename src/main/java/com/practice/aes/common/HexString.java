package com.practice.aes.common;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

public class HexString {
	public static String toHexadecimal(String text) {
		byte[] myBytes;
		try {
			myBytes = text.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			myBytes = text.getBytes();
		}
		return DatatypeConverter.printHexBinary(myBytes);
	}

	public static String toString(String text) {
		byte[] bytes = DatatypeConverter.parseHexBinary(text);
		try {
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// e.printStackTrace();
			return new String(bytes);
		}
	}
}
