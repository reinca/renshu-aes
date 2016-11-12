package com.practice.test;

import static org.junit.Assert.assertEquals;

import java.time.OffsetDateTime;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practice.aes.Application;
import com.practice.aes.service.CipherService;

public class Password {
	private static final Logger logger = LoggerFactory.getLogger(Password.class);
	private CipherService cs = new CipherService();
	private OffsetDateTime id = Application.UP_DATE;

	// String to String
	@Test
	public void passwordTest1() throws Exception {
		String id = sentenceGenerator(24);
		String date32 = OffsetDateTime.now().toString();
		logger.info("Source ID:	" + id);
		String enId = cs.encode(id, date32);
		logger.info("Encrypted ID:	" + enId);
		String desId = cs.decode(enId, date32);
		logger.info("Decrypted ID:	" + desId);
		assertEquals(id, desId);
	}
	
	// Date to String
	@Test
	public void passwordTest2() throws Exception {
		OffsetDateTime date32 = OffsetDateTime.now();
		logger.info("Source ID:	" + id);
		String enId = cs.encode(id, date32);
		logger.info("Encrypted ID:	" + enId);
		String desId = cs.decode(enId, date32);
		logger.info("Decrypted ID:	" + desId);
		assertEquals(id.toString(), desId);
		
	}

	private String sentenceGenerator(long length) {
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
