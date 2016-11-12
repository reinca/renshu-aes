package com.practice.aes.service;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.time.OffsetDateTime;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practice.aes.common.ArrayListChanger;

public class CipherService {

	private static final Logger logger = LoggerFactory.getLogger(CipherService.class);
	private static final String INSTANCE ="AES/CBC/PKCS5Padding";
	private static final String DEFAULT_KEY = "1234567890ABCDEFFEDCBA0987654321"; // 32bit default
	private static byte[] IV = new byte[16];

	public String encode(OffsetDateTime currentTime, OffsetDateTime baseTime) throws Exception {
		return encode(currentTime.toString(), baseTime.toString());
	}

	public String decode(String encryptedText, OffsetDateTime baseTime) throws Exception {
		return decode(encryptedText, baseTime.toString());
	}

	public String encode(String currentTime, String baseTime) throws Exception {
	    Key keySpec = getAESKey(baseTime, true);
	    Cipher c;
	    try {
	    	c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(IV));
		} catch (InvalidKeyException e) {
		    keySpec = getAESKey(baseTime, false);
		    c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(IV));
		}
	    byte[] encrypted = c.doFinal(currentTime.getBytes("UTF-8"));
	    String result = new String(Base64.getEncoder().encodeToString(encrypted));
	    return result;
	}

	public String decode(String encryptedText, String baseTime) throws Exception {
	    Key keySpec = getAESKey(baseTime, true);
	    Cipher c;
	    try {
	    	c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(IV));
		} catch (InvalidKeyException e) {
		    keySpec = getAESKey(baseTime, false);
	    	c = Cipher.getInstance(INSTANCE);
			c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(IV));
		}
	    byte[] byteStr = Base64.getDecoder().decode(encryptedText.getBytes("UTF-8"));
	    String result = new String(c.doFinal(byteStr), "UTF-8");
	    return result;
	}
	
	private Key getAESKey(String timeKey, boolean size) {
		String date32 = String.format("%32s", timeKey).replace(' ', '0');
	    Key keySpec;
	    String key = (timeKey != null) ? date32 : DEFAULT_KEY;
	    byte[] keyBytes = new byte[16];
	    if(size == true) keyBytes = new byte[32];
	    byte[] bTmp;
		try {
			bTmp = key.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString());
			bTmp = DEFAULT_KEY.getBytes();
		}
		List<Byte> keyList = ArrayListChanger.byteArrayToList(bTmp);
		Collections.reverse(keyList);
		byte[] ivBytes = ArrayListChanger.byteListToArray(keyList);
	    int len = bTmp.length;
	    if (len > keyBytes.length) {
	       len = keyBytes.length;
	    }

	    System.arraycopy(bTmp, 0, keyBytes, 0, len);
	    System.arraycopy(ivBytes, 0, IV, 0, IV.length);
	    logger.debug(size + " :: " + new String(keyBytes));
	    keySpec = new SecretKeySpec(keyBytes, "AES");

	    return keySpec;
	}
}
