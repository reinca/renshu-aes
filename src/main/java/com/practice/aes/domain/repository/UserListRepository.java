package com.practice.aes.domain.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface UserListRepository<T, ID extends Serializable> extends Repository<T, ID> {
	T findOneByUid(String id);
	List<T> findAll();
	List<T> findAll(Sort sort);
}
