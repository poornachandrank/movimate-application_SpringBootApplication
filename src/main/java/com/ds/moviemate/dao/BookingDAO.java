package com.ds.moviemate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.repository.BookingRepository;

@Component
public class BookingDAO {
	@Autowired
	public BookingRepository bookingRepository;

	
	public BookingEntity saveBookingsDataToDB(BookingEntity newBooking){
		return 	bookingRepository.save(newBooking);			

					
	}
	
	public List<BookingEntity> 	findBookingByUserID(UserEntity userDataObj) {
		return bookingRepository.findByUserId(userDataObj);
		
	}

	public void deleteByBookingsId(long bookingId) {
		bookingRepository.deleteById(bookingId);		
	}

	public List<BookingEntity> getAllUserBooking() {
	return 	bookingRepository.findAll();		
	}

	
}
