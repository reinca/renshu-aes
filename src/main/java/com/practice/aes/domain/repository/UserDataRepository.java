package com.practice.aes.domain.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.aes.domain.model.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	void deleteByUid(String uid);
	UserData findOneByUid(String uid);
	List<UserData> findAll();
	List<UserData> findAll(Sort sort);
}
