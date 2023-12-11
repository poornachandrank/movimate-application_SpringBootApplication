package com.ds.moviemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.moviemate.model.ScreenEntity;
public interface ScreenRepository extends JpaRepository<ScreenEntity, Long> {


	ScreenEntity findByScreenName(String screenNumber);


	
}
