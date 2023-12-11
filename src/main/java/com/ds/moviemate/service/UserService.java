package com.ds.moviemate.service;

import java.security.Principal;
import java.util.List;

import com.ds.moviemate.dto.PasswordDTO;
import com.ds.moviemate.dto.UserDTO;
import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.UserEntity;

public interface UserService {

	
	public String updatePass(PasswordDTO passwordDTO, Principal principal);
	public UserEntity newUser(UserDTO userEntity);
	public List<BookingEntity> getUserBookingData(Principal userBooking);
	public List<UserEntity> getAllUserData();
}

