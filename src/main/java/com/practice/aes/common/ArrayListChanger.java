package com.practice.aes.common;

import java.util.ArrayList;
import java.util.List;

// Because storage capacity, abanndoned using commons-lang
public class ArrayListChanger {
	public static List<Byte> byteArrayToList(byte[] src) {
		List<Byte> result = new ArrayList<Byte>();
		for (byte des : src) {
			result.add(des);
		}
		return result;
	}

	public static byte[] byteListToArray(List<Byte> src) {
		byte[] result = new byte[src.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = src.get(i);
		}
		return result;
	}
}
