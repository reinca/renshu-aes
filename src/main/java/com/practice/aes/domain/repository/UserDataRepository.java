package com.practice.aes.domain.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.aes.domain.model.UserData;

@Transactional
public interface UserDataRepository extends JpaRepository<UserData, Long> {
	void deleteByUid(String id);
	UserData findOneByUid(String id);
	List<UserData> findAll();
	List<UserData> findAll(Sort sort);
}
