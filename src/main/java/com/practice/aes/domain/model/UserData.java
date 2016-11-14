package com.practice.aes.domain.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.practice.aes.Application;

@Entity
@Table(name="userauth")
public class UserData {

	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	@JsonIgnore
	private long id;						// Unique id for database
	private String uid;						// Unique id for server-client
	private String encryptedKey;			// Encrypted Key based baseTime and loggedTime
	private OffsetDateTime baseTime;		// Used as IV key, application start time (aes.Application)
	private OffsetDateTime requestedTime;		// Used as private key, time for requested

	public UserData(){}
	
	public UserData(String uid) {
		this.uid = uid;
		this.encryptedKey = null;
		this.baseTime = Application.UP_DATE;
		this.requestedTime = OffsetDateTime.now();
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

	public OffsetDateTime getRequestedTime() {
		return requestedTime;
	}

	public void setRequestedTime() {
		this.requestedTime = OffsetDateTime.now();
	}

	public void setRequestedTime(OffsetDateTime RequestedTime) {
		this.requestedTime = RequestedTime;
	}

	@Override
	public String toString() {
		return "UserData [ID=" + id + ", Unique ID=" + uid + ", Encrypted Key=" + encryptedKey + ", Base Time=" + baseTime
				+ ", Requested Time=" + requestedTime + "]";
	}
	
}
