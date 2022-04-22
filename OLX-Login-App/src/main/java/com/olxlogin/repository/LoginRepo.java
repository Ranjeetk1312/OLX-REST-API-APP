package com.olxlogin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olxlogin.entity.LoginEntity;

public interface LoginRepo extends JpaRepository<LoginEntity, Integer> {
	
	List<LoginEntity> findByUserName(String stockName);
	 
}
