package com.practice.aes.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.time.OffsetDateTime;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CipherService {

	private static final Logger logger = LoggerFactory.getLogger(CipherService.class);
	private static final String INSTANCE = "AES/CBC/PKCS5Padding";
	private static final String DEFAULT_KEY = "1234567890ABCDEFFEDCBA0987654321"; // 32bits
	private static IvParameterSpec IV; // private static byte[] IV = new byte[16]; // For AES128/AES256, both using 16bits

	public String encode(OffsetDateTime currentTime, OffsetDateTime baseTime) throws Exception {
		return encode(currentTime.toString(), baseTime.toString());
	}

	public String decode(String encryptedText, OffsetDateTime currentTime, OffsetDateTime baseTime) throws Exception {
		return decode(encryptedText, currentTime.toString(), baseTime.toString());
	}

	public String encode(String currentTime, String baseTime) throws Exception {
		Key keySpec = getAESKey(currentTime, baseTime, true);
		Cipher c;
		try {
			c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.ENCRYPT_MODE, keySpec, IV);
		} catch (InvalidKeyException e) { // If AES256 not supported, execute as
											// AES128
			keySpec = getAESKey(currentTime, baseTime, false);
			c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.ENCRYPT_MODE, keySpec, IV);
		}
		byte[] encrypted = c.doFinal(currentTime.getBytes("UTF-8"));
		String result = new String(Base64.getEncoder().encodeToString(encrypted));
		return result;
	}

	public String decode(String encryptedText, String currentTime, String baseTime) throws Exception {
		Key keySpec = getAESKey(currentTime, baseTime, true);
		Cipher c;
		try {
			c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.DECRYPT_MODE, keySpec, IV);
		} catch (InvalidKeyException e) { // If AES256 not supported, execute as
											// AES128
			keySpec = getAESKey(currentTime, baseTime, false);
			c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.DECRYPT_MODE, keySpec, IV);
		}
		byte[] byteStr = Base64.getDecoder().decode(encryptedText.getBytes("UTF-8"));
		String result = new String(c.doFinal(byteStr), "UTF-8");
		return result;
	}

	private Key getAESKey(String currentTime, String baseTime, boolean size) {
		// key, iv parsing, padding for 32bits
		String keyString = (currentTime != null) ? String.format("%32s", currentTime).replace(' ', '0') : DEFAULT_KEY;
		String iv = (baseTime != null) ? String.format("%32s", currentTime).replace(' ', '0') : DEFAULT_KEY;
		String ivString = new StringBuffer(iv).reverse().toString();
		// set new(?) key
		Key keySpec;
		// set length key 32(AES256) or 16(AES128)
		byte[] keyBytes = (size == true) ? new byte[32] : new byte[16];
		// set length iv 16(128/256 both)
		byte[] ivBytes = new byte[16];
		// set temporary byte array
		byte[] keyTmp;
		byte[] ivTmp;
		try {
			keyTmp = keyString.getBytes("UTF-8");
			ivTmp = ivString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) { // If character doesn't support unicode, use default key
			keyTmp = DEFAULT_KEY.getBytes();
			ivTmp = new StringBuffer(DEFAULT_KEY).reverse().toString().getBytes();
			logger.error(e.toString());
		}
		int keyLength = (keyTmp.length > keyBytes.length) ? keyBytes.length : keyTmp.length;
		int ivLength = (ivTmp.length > ivBytes.length) ? ivBytes.length : ivTmp.length;

		System.arraycopy(keyTmp, 0, keyBytes, 0, keyLength);
		System.arraycopy(ivTmp, 0, ivBytes, 0, ivLength);
		logger.debug(size + " :: " + new String(keyBytes));
		keySpec = new SecretKeySpec(keyBytes, "AES");
		IV = new IvParameterSpec(ivBytes);

		return keySpec;
	}
}
