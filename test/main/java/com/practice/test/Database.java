package com.practice.test;

import org.junit.Test;
import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.practice.aes.domain.entity.UserData;
import com.practice.aes.service.AESService;

public class Database {
	private static final Logger logger = LoggerFactory.getLogger(Database.class);
	@Autowired AESService aess;

	@Test
	public void dataAddTest() {
		UserData testData = new UserData();
		logger.info(testData.toString());
		aess.add(testData);
		UserData result = aess.find(testData.getId());
		assertEquals(result, testData);
		aess.delete();
	}
}
