package com.practice.aes.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.aes.Application;
import com.practice.aes.domain.entity.Result;
import com.practice.aes.domain.entity.UserData;
import com.practice.aes.domain.repository.UserDataRepository;

@Service
public class AESService {

	private static final Logger logger = LoggerFactory.getLogger(AESService.class);
	@Autowired private UserDataRepository udr;
	private CipherService cs = new CipherService();

	public UserData find(long id) {
		return udr.findOne(id);
	}

	public List<UserData> list() {
		return udr.findAll();
	}

	public Result reset() {
		udr.deleteAll();
		return new Result(0, "reseted");
	}

	public Result delete(long id) {
		udr.delete(id);
		return new Result(id, "success");
	}

	public Result addUser(long id) {
		UserData result = new UserData(id);
		try {
			String enc = cs.encode(result.getLoggedTime(), result.getBaseTime());
			result.setEncryptedKey(enc);
			udr.save(result);
			return new Result(id, "success");
		} catch (Exception e) {
			logger.error(e.toString());
			return new Result(id, "failed");
		}

	}

	public Result refreshPassword(long id) {
		UserData result = udr.findOne(id);
		OffsetDateTime baseTimeTmp = Application.UP_DATE;
		OffsetDateTime loggedTimeTmp = OffsetDateTime.now();
		try {
			String enc = cs.encode(loggedTimeTmp, baseTimeTmp);
			result.setBaseTime(baseTimeTmp);
			result.setLoggedTime(loggedTimeTmp);
			result.setEncryptedKey(enc);
			udr.save(result);
			return new Result(id, "success");
		} catch (Exception e) {
			logger.error(e.toString());
			return new Result(id, "failed");
		}
	}
}
