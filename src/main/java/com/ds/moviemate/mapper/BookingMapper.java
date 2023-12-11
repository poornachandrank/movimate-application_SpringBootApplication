package com.ds.moviemate.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ds.moviemate.dto.BookingDTO;
import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.model.UserEntity;

@Component
public class BookingMapper {
	
	public BookingEntity bookingMapper(BookingDTO bookings, UserEntity userId, ShowEntity show) {
		BookingEntity newBooking = new BookingEntity();
		newBooking.setSeatCount(bookings.getSeatCount());
		newBooking.setUserId(userId);
		newBooking.setShowId(show);
		newBooking.setSeatName(bookings.getSeatName());
		newBooking.setSeatPosition(bookings.getCoordinate());
		newBooking.setTotalPrice(bookings.getTotalPrice());
		return newBooking;
	}
	
	
	public List<BookingDTO> mapUserListToDto(List<BookingEntity> allBookings) {
		return allBookings.stream().map(this::mapbookings).collect(Collectors.toList());
	}
	

	public BookingDTO mapbookings(BookingEntity bookinkings) {
		BookingDTO bookingdto = new BookingDTO();
		bookingdto.setBookingId(bookinkings.getBookingId());
		bookingdto.setBookingStatus(bookinkings.getBookingStatus());
		bookingdto.setCreationtime(bookinkings.getCreationtime());
		bookingdto.setPaymentStatus(bookinkings.getPaymentStatus());
		bookingdto.setSeatCount(bookinkings.getSeatCount());
		bookingdto.setSeatName(bookinkings.getSeatName());
		bookingdto.setSeatPosition(bookinkings.getSeatPosition());
		bookingdto.setTotalPrice(bookinkings.getTotalPrice());
		bookingdto.setUserId(bookinkings.getUserId());
		bookingdto.setSeatName(bookinkings.getSeatName());
		bookingdto.setShowIds(bookinkings.getShowId());
		return  bookingdto;

	}
	

}
