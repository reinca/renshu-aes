package com.practice.aes.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.practice.aes.Application;

@Entity
public class UserData {

	@Id @GeneratedValue(strategy=GenerationType.AUTO) private long id;	// Unique id for database
	private String uid;						// Unique id for server-client
	private String encryptedKey;			// Encrypted Key based baseTime and loggedTime
	private OffsetDateTime baseTime;		// Used as IV key, application start time (aes.Application)
	private OffsetDateTime currentTime;		// Used as private key, time for requested

	public UserData(){}
	
	public UserData(String uid) {
		this.uid = uid;
		this.encryptedKey = null;
		this.baseTime = Application.UP_DATE;
		this.currentTime = OffsetDateTime.now();
	}

	public long getId() {
		return id;
	}
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
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

	public OffsetDateTime getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime() {
		this.currentTime = OffsetDateTime.now();
	}

	public void setCurrentTime(OffsetDateTime currentTime) {
		this.currentTime = currentTime;
	}

	@Override
	public String toString() {
		return "UserData [id=" + id + ", uid=" + uid + ", encryptedKey=" + encryptedKey + ", baseTime=" + baseTime
				+ ", loggedTime=" + currentTime + "]";
	}
	
}
