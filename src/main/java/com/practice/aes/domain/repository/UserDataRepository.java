package com.practice.aes.domain.repository;

import javax.transaction.Transactional;

import com.practice.aes.domain.model.UserData;

@Transactional
public interface UserDataRepository extends UserListRepository<UserData, Long> {
	void deleteByUid(String id);
	void delete(UserData user);
	void deleteAll();
	void save(UserData result);
	void saveAndFlush(UserData result);
}
