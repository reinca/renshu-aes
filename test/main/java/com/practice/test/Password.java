package com.practice.test;

import static org.junit.Assert.assertEquals;

import java.time.OffsetDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import com.practice.aes.Application;
import com.practice.aes.common.StringUtil;
import com.practice.aes.service.CipherService;

@RunWith(SpringRunner.class)
// Password Encode/Decode, Hex String Test
public class Password {
	private static final Logger logger = LoggerFactory.getLogger(Password.class);
	private CipherService cs = new CipherService();

	// String to String
	// In this case, currentTime isn't used as key string
	@Test
	public void passwordTest1() throws Exception {
		String id = StringUtil.sentenceGenerator(24);
		OffsetDateTime timePK = OffsetDateTime.now();
		OffsetDateTime timeIV = Application.UP_DATE;
		logger.info("Source ID:	" + id);
		String enId = cs.encode(id, timePK, timeIV);
		logger.info("Encrypted ID:	" + enId);
		String desId = cs.decode(enId, timePK, timeIV);
		logger.info("Decrypted ID:	" + desId);
		assertEquals(id, desId);
	}

	// Date to String
	// In this case, currentTime is used to key string and private key
	@Test
	public void passwordTest2() throws Exception {
		OffsetDateTime id = OffsetDateTime.now();
		OffsetDateTime timeIV = Application.UP_DATE;
		logger.info("Source ID:	" + id);
		String enId = cs.encode(id, timeIV);
		logger.info("Encrypted ID:	" + enId);
		String desId = cs.decode(enId, id, timeIV);
		logger.info("Decrypted ID:	" + desId);
		assertEquals(id.toString(), desId);
		
	}
	
	// String to HEX to String
	@Test
	public void hexTest() throws Exception {
		String id = StringUtil.sentenceGenerator(24);
		String hexId = StringUtil.toHexString(id);
		String result = StringUtil.toString(hexId);
		assertEquals(id, result);
	}
}
