package com.ds.moviemate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.service.serviceimplementaion.UserServiceImplementaion;

@Controller
public class ViewController {
	@Autowired
	UserServiceImplementaion userServiceImplementaion;
	
	@GetMapping("/Dashboard")
	public String dashBoardPage() {
		return "DashBoard";
	}
	@GetMapping("/ShowMasterPage")
	public String showMasterPage() {
		return "Master/Show-Master";
	}

	@GetMapping("/cinemaMasterPage")
	public String cinemaMasterPage() {
		return "Master/Cinema-Master";
	}

	@GetMapping("/movieMasterPage")
	public String movieMasterPage() {
		return "Master/Movie-Master";
	}
	@GetMapping("/screenMasterPage")
	public String screenMasterPage() {
		return "Master/Screen-Master";
	}
	@GetMapping("/createShowMasterPage")
	public String createShowMasterPage() {
		return "Master/Show-Master";
	}

	@GetMapping("/bookings")
	public String showAllBookigsPage() {

		return "Master/UserBookings";
	}
	
	@GetMapping("/home")
    public String showLoginForm(Model model) {
         
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "Default-home-page";
        }
 
        return "redirect:/User_Home";
    }
	
	@GetMapping("/logout-page")
	public String logoutPage() {
		return "Authentication/logout-page";
}
	
	@GetMapping("/allUsers")
	public ModelAndView getAllUsers(ModelAndView modelAndView) {
		List<UserEntity> alluserData = userServiceImplementaion.getAllUserData();
	 	modelAndView.setViewName("Master/Registred-Users");
		modelAndView.addObject("UserData", alluserData);
		return modelAndView;
	}

	
}
