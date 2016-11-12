package com.practice.aes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.aes.domain.entity.UserData;
import com.practice.aes.domain.repository.UserDataRepository;

@Service
public class AESService {

	@Autowired UserDataRepository udr;

	public UserData find(long id) {
		return udr.findOne(id);
	}
	
	public void add(UserData data) {
		udr.save(data);
	}
	
	public void delete(){
		udr.deleteAll();
	}
}
