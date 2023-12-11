package com.ds.moviemate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ds.moviemate.dto.BookingDTO;
import com.ds.moviemate.service.serviceimplementaion.BookingServiceImplementation;

@Controller
public class BookingsController {
	@Autowired
	BookingServiceImplementation bookingServiceImplementation;

	@GetMapping("/allbookings")
	public ResponseEntity<List<BookingDTO>> allBookings(ModelAndView modelAndView) {
		List<BookingDTO> allUserBookingsDetails = bookingServiceImplementation.getallUserBookingsDetails();
		return new  ResponseEntity<>(allUserBookingsDetails,HttpStatus.OK);

	}



}
