package com.olxlogin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olxlogin.entity.AdvertiseEntity;


public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer> {
	
	List<AdvertiseEntity> findByUsername(String usernmae);


}
