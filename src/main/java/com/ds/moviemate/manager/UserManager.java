package com.ds.moviemate.manager;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ds.moviemate.dao.BookingDAO;
import com.ds.moviemate.dao.UserDAO;
import com.ds.moviemate.dto.PasswordDTO;
import com.ds.moviemate.dto.UserDTO;
import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.RoleEntity;
import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.repository.RoleRepository;
@Component
public class UserManager {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	BookingDAO bookingDAO;
	public UserEntity saveUser(UserDTO newUser) {
		RoleEntity role = null;
		Optional<RoleEntity> data = roleRepository.findById(1l);
		if (data.isPresent()) {
			role = data.get();
		}
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		UserEntity actualNewUser = new UserEntity();
		actualNewUser.setFullName(newUser.getFullName());
		actualNewUser.setAge(newUser.getAge());
		actualNewUser.setContactNumber(newUser.getContactNumber());
		actualNewUser.setUserName(newUser.getUserName());
		actualNewUser.setEmail(newUser.getEmail());
		actualNewUser.setPassword((bcrypt.encode(newUser.getPassword())));
		actualNewUser.setRole(role);
		// save the user to DB and return
		return userDAO.saveNewUser(actualNewUser);		
	}
	public UserEntity getUserDataByEmailId(String emailId) {
	 return userDAO.findUserByEmail(emailId);	
	}
	public UserEntity getById(String emailId) {
		return userDAO.findUserByEmail(emailId);		
	}
	public String updatePass(PasswordDTO passwordDTO, Principal principal) {
		String userEmailId = principal.getName();

		UserEntity userExist = userDAO.findUserByEmail(userEmailId);

		if (userExist.getPassword().equals(passwordDTO.getCurrentPassword())) {
			String newPassword = passwordDTO.getNewPassword();
			userExist.setPassword(newPassword);
			userDAO.updateUser(userExist);
			return "success";
		} else
			return "currentPassWordIsWrong";
		
	}
	public List<BookingEntity> getUserBookingData(Principal userBooking) {

		// find user by email
		UserEntity userData = userDAO.findUserByEmail(userBooking.getName());
		// find booked data by user ID
		return bookingDAO.findBookingByUserID(userData);		
	}
	public List<UserEntity> getAllUserdata() {
		return userDAO.findAllUserData();		
	}

}
