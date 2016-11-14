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

// Encode/Decode method based AES256-CBC or AES128-CBC
// Priority: AES256 first, AES128 second
public class CipherService {

	private static final Logger logger = LoggerFactory.getLogger(CipherService.class);
	private static final String INSTANCE = "AES/CBC/PKCS5Padding";
	private static final String DEFAULT_KEY = "1234567890ABCDEFFEDCBA0987654321"; // 32bits

	// In this case, requestedTime is used to key string and private key
	public String encode(OffsetDateTime requestedTime, OffsetDateTime baseTime) throws Exception {
		return encode(requestedTime.toString(), requestedTime.toString(), baseTime.toString());
	}
	
	// In this case, requestedTime isn't used as key string
	public String encode(String sourceText, OffsetDateTime requestedTime, OffsetDateTime baseTime) throws Exception {
		return encode(sourceText, requestedTime.toString(), baseTime.toString());
	}

	// In this case, requestedTime is used to key string and private key
	public String decode(OffsetDateTime requestedTime, OffsetDateTime baseTime) throws Exception {
		return decode(requestedTime.toString(), requestedTime.toString(), baseTime.toString());
	}

	// In this case, requestedTime isn't used as key string
	public String decode(String encryptedText, OffsetDateTime requestedTime, OffsetDateTime baseTime) throws Exception {
		return decode(encryptedText, requestedTime.toString(), baseTime.toString());
	}

	public String encode(String sourceText, String privateKey, String ivKey) throws Exception {
		Cipher c = getCipher(Cipher.ENCRYPT_MODE, privateKey, ivKey);
		// String to byte array, after encode as base64
		byte[] byteStr = c.doFinal(sourceText.getBytes("UTF-8"));
		String result = new String(Base64.getEncoder().encodeToString(byteStr));
		return result;
	}

	public String decode(String encryptedText, String privateKey, String ivKey) throws Exception {
		Cipher c = getCipher(Cipher.DECRYPT_MODE, privateKey, ivKey);
		// decode as byte array, after decode
		byte[] byteStr = Base64.getDecoder().decode(encryptedText.getBytes("UTF-8"));
		String result = new String(c.doFinal(byteStr), "UTF-8");
		return result;
	}

	private Cipher getCipher(int mode, String text, String privateKey) throws Exception {
		Key keySpec;
		IvParameterSpec iv = getIVSpec(privateKey);
		Cipher c;
		try {
			keySpec = getKeySpec(text, true);
			c = Cipher.getInstance(INSTANCE);
			c.init(mode, keySpec, iv);
		} catch (InvalidKeyException e) { // If AES256 not supported, execute as AES128
			keySpec = getKeySpec(text, false);
			c = Cipher.getInstance(INSTANCE);
			c.init(mode, keySpec, iv);
		}
		return c;
	}

	private Key getKeySpec(String sourceText, boolean size) {
		// set length key 32(AES256) or 16(AES128)
		int aesSize = (size == true) ? 32 : 16;
		// source text parsing, padding for 32bits
		String key = (sourceText != null) ? String.format("%32s", sourceText).replace(' ', '0') : DEFAULT_KEY;
		// Cut or save, by compare AES256/128
		String keyString = key.substring(0,  aesSize); 
		byte[] keyBytes = new byte[aesSize];
		try {
			keyBytes = keyString.substring(0, aesSize).getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) { // If character doesn't support unicode, use default key
			keyBytes = DEFAULT_KEY.substring(0, aesSize).getBytes();
			logger.error(e.toString());
		}
		logger.debug("pKEY	:: " + new String(keyBytes));

		return new SecretKeySpec(keyBytes, "AES");
	}
	
	private IvParameterSpec getIVSpec(String privateKey) {
		// iv parsing, padding for 32bits
		int ivSize = 16;
		String iv = (privateKey != null) ? String.format("%32s", privateKey).replace(' ', '0') : DEFAULT_KEY;
		// After 32bit reverse, cut 16bits(AES128/256 both)
		String ivString = new StringBuffer(iv).reverse().toString().substring(0, ivSize);
		byte[] ivBytes = new byte[ivSize];
		try {
			ivBytes = ivString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) { // If character doesn't support unicode, use default key
			ivBytes = new StringBuffer(DEFAULT_KEY).reverse().toString().getBytes();
			logger.error(e.toString());
		}
		logger.debug("IV	:: " + new String(ivBytes));

		return new IvParameterSpec(ivBytes);
	}
}
