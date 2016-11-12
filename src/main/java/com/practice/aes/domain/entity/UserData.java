package com.practice.aes.domain.entity;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.practice.aes.Application;

@Entity
public class UserData {

	@Id @GeneratedValue
	private long id;
	private String encryptedKey;
	private OffsetDateTime baseTime;
	private OffsetDateTime loggedTime;

	public UserData() {
		this.encryptedKey = null;
		this.baseTime = Application.UP_DATE;
		this.loggedTime = OffsetDateTime.now();
	}

	public UserData(long id) {
		this.id = id;
		this.encryptedKey = null;
		this.baseTime = Application.UP_DATE;
		this.loggedTime = OffsetDateTime.now();
	}

	public long getId() {
		return id;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public void setEncryptedKey(String encryptedKey) {
		this.encryptedKey = encryptedKey;
	}

	public OffsetDateTime getBaseTime() {
		return baseTime;
	}

	public void setBaseTime() {
		this.baseTime = Application.UP_DATE;
	}

	public void setBaseTime(OffsetDateTime baseTime) {
		this.baseTime = baseTime;
	}

	public OffsetDateTime getLoggedTime() {
		return loggedTime;
	}

	public void setLoggedTime() {
		this.loggedTime = OffsetDateTime.now();
	}

	public void setLoggedTime(OffsetDateTime loggedTime) {
		this.loggedTime = loggedTime;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + ", encryptedKey=" + encryptedKey + ", baseTime=" + baseTime + ", loggedTime="
				+ loggedTime + "]";
	}

}
