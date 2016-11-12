package com.practice.aes.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.aes.domain.entity.UserData;

public interface UserDataRepository extends JpaRepository<UserData, Long> {
	
}
