package com.ds.moviemate.service.serviceimplementaion;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dto.PasswordDTO;
import com.ds.moviemate.dto.UserDTO;
import com.ds.moviemate.manager.UserManager;
import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.service.UserService;

@Service
public class UserServiceImplementaion implements UserService {

	@Autowired
	UserManager userManager;

	// save
	@Override
	public UserEntity newUser(UserDTO newUser) {
		return userManager.saveUser(newUser);
	}

	// getById
	public UserEntity getUserDataByEmailId(String emailId) {
		return userManager.getById(emailId);
	}

//updatePass
	public String updatePass(PasswordDTO passwordDTO, Principal principal) {
		return userManager.updatePass(passwordDTO, principal);
	}

//getBookings
	public List<BookingEntity> getUserBookingData(Principal userBooking) {

		return userManager.getUserBookingData(userBooking);

	}
//getAll
	public List<UserEntity> getAllUserData() {
		return userManager.getAllUserdata();
	}

}
