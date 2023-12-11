package com.ds.moviemate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.repository.UserRepository;
@Component
public class UserDAO {
	
	@Autowired
	 public UserRepository userRepository;
	
	public UserEntity findUserByEmail(String userEmailId) {
		return userRepository.findByEmail(userEmailId);
		
	}

	public UserEntity saveNewUser(UserEntity actualNewUser) {
		return userRepository.save(actualNewUser);
		
	}
	public UserEntity updateUser(UserEntity actualNewUser) {
		return userRepository.save(actualNewUser);
		
	}

	public List<UserEntity> findAllUserData() {
		return userRepository.findAll();		
	}
	
	
	


}
