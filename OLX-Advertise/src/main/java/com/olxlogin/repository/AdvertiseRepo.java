package com.olxlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.olxlogin.entity.AdvertiseEntity;


public interface AdvertiseRepo extends JpaRepository<AdvertiseEntity, Integer> {

}
