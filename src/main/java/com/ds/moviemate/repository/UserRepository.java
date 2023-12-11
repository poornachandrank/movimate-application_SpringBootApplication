package com.ds.moviemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.UserEntity;
@Component
public interface UserRepository extends JpaRepository<UserEntity, Long> {


	UserEntity findByEmail(String username);


	
}
