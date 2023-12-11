package com.ds.moviemate.service.serviceimplementaion;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dto.BookingDTO;
import com.ds.moviemate.manager.BookingManager;
import com.ds.moviemate.mapper.BookingMapper;
import com.ds.moviemate.model.BookingEntity;

@Service
public class BookingServiceImplementation {
	@Autowired
	BookingManager manager;
	@Autowired
	BookingMapper bookingMapper;

	// getuserBooking
	public List<BookingDTO> getallUserBookingsDetails() {
		return bookingMapper.mapUserListToDto(manager.getAllBookings());
	}

	// saveNewbooking
	public BookingEntity saveBookings(BookingDTO bookings, Principal user) {
		return manager.saveBookings(bookings, user);
	}

	// deleteBooking(functionality is diabled)
	public boolean cancelBooking(int seatCount, long seatId, long bookingId, String userEmail) {
		return manager.deleteBooking(seatCount, seatId, bookingId, userEmail);
	}

}
