package com.practice.aes.domain.entity;

import java.time.OffsetDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.practice.aes.Application;

@Entity
public class UserData {

	@Id
	@GeneratedValue
	private long id;
	private String encryptedKey;
	private OffsetDateTime baseTime;
	private OffsetDateTime loggedTime;

	public UserData() {
		this.encryptedKey = "bulkTest";
		this.baseTime = Application.UP_DATE;
		this.loggedTime = OffsetDateTime.now();
	}

	public UserData(String encryptedKey) {
		this.encryptedKey = encryptedKey;
		this.baseTime = Application.UP_DATE;
		this.loggedTime = OffsetDateTime.now();
	}

	public long getId() {
		return id;
	}

	public String getEncryptedKey() {
		return encryptedKey;
	}

	public OffsetDateTime getBaseTime() {
		return baseTime;
	}

	public OffsetDateTime getLoggedTime() {
		return loggedTime;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + ", encryptedKey=" + encryptedKey + ", baseTime=" + baseTime + ", loggedTime="
				+ loggedTime + "]";
	}

}
