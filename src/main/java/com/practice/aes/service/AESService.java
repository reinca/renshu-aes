package com.practice.aes.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.practice.aes.Application;
import com.practice.aes.common.StringUtil;
import com.practice.aes.domain.model.Result;
import com.practice.aes.domain.model.UserData;
import com.practice.aes.domain.repository.UserDataRepository;

@Service
public class AESService {

	private static final Logger logger = LoggerFactory.getLogger(AESService.class);
	@Autowired private UserDataRepository udr;
	private CipherService cs = new CipherService();

	@Transactional(readOnly = true)
	public List<UserData> list() {
		return udr.findAll();
	}

	@Transactional(readOnly = true)
	public UserData find(String id) {
		return udr.findOneByUid(id);
	}

	// Compare both hex-encrypted key
	@Transactional(readOnly = true)
	public Result auth(String id, String key) {
		UserData user = udr.findOneByUid(id);
		String hexKey = StringUtil.toHexString(user.getEncryptedKey());
		logger.info(user.getEncryptedKey());
		String authResult = (key.equals(hexKey)) ? "success" : "failed";
		return new Result(id, authResult);
	}

	public Result reset() {
		udr.deleteAll();
		return new Result("reset", "success");
	}

	public Result delete(String id) {
		udr.deleteByUid(id);
		return new Result(id, "success");
	}

	public Result addUser(String id) {
		// If same ID exists, return failed
		if(udr.findOneByUid(id) != null) return new Result(id, "failed");
		UserData result = new UserData(id);
		try {
			String enc = cs.encode(result.getRequestedTime(), result.getBaseTime());
			result.setEncryptedKey(enc);
			udr.save(result);
			logger.debug(result.toString());
			return new Result(id, "success");
		} catch (Exception e) {
			logger.error(e.toString());
			return new Result(id, "failed");
		}
	}

	public Result refreshPassword(String id) {
		// If ID doesn't exists, return failed
		if(udr.findOneByUid(id) == null) return new Result(id, "failed");
		UserData result = udr.findOneByUid(id);
		OffsetDateTime requestedTimeTmp = OffsetDateTime.now();
		OffsetDateTime baseTimeTmp = Application.UP_DATE;
		try {
			String enc = cs.encode(requestedTimeTmp, baseTimeTmp);
			result.setRequestedTime(requestedTimeTmp);
			result.setBaseTime(baseTimeTmp);
			result.setEncryptedKey(enc);
			udr.saveAndFlush(result);
			logger.debug(result.toString());
			return new Result(id, "success");
		} catch (Exception e) {
			logger.error(e.toString());
			return new Result(id, "failed");
		}
	}

	// Because of security problem, decode function unsupported
/*	public Result decodePassword(String id) {
		// If ID doesn't exists, return failed
		if(udr.findOneByUid(id) != null) return new Result(id, "failed");
		UserData result = new UserData(id);
		try {
			String enc = cs.decode(result.getEncryptedKey(), result.getRequestedTime(), result.getBaseTime());
			logger.debug(result.toString());
			return new Result(id, enc);
		} catch (Exception e) {
			logger.error(e.toString());
			return new Result(id, "failed");
		}
	}*/
}
