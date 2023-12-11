package com.ds.moviemate.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.SeatEntity;
import com.ds.moviemate.repository.SeatRepository;
@Component
public class SeatDAO {

	@Autowired
	SeatRepository seatRepository;

	
	public SeatEntity saveBookings(SeatEntity seat) {
		
	 return seatRepository.save(seat);
	}
}
