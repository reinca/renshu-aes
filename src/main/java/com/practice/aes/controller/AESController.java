package com.practice.aes.controller;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.aes.domain.entity.UserData;
import com.practice.aes.domain.repository.UserDataRepository;

@RestController
public class AESController {

	private static final Logger logger = LoggerFactory.getLogger(AESController.class);
	
	@Autowired UserDataRepository userData;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public UserData greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		OffsetDateTime currentDate = OffsetDateTime.now();
		UserData result = new UserData(name);
		userData.save(result);
		logger.info(userData.findAll().toString());
		userData.deleteAll();
		return result;
	}
}

// default: getTime
// if null: nowTime