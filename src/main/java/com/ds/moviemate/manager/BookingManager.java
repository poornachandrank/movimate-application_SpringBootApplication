package com.ds.moviemate.manager;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.dao.BookingDAO;
import com.ds.moviemate.dao.SeatDAO;
import com.ds.moviemate.dao.ShowDAO;
import com.ds.moviemate.dao.UserDAO;
import com.ds.moviemate.dto.BookingDTO;
import com.ds.moviemate.mapper.BookingMapper;
import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.SeatEntity;
import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.model.UserEntity;

@Component
public class BookingManager {

	@Autowired
	BookingDAO bookingDao;
	@Autowired
	ShowDAO showDAO;
	@Autowired
	UserDAO userDAO;
	@Autowired
	SeatDAO seatDAO;
	@Autowired
	BookingMapper bookingMapper;

	// get All
	public List<BookingEntity> getAllBookings() {
		return bookingDao.getAllUserBooking();
				
				
	}

	/* save New Bookings */

	public BookingEntity saveBookings(BookingDTO bookings, Principal user) {
		ShowEntity show = getShowById(bookings);
		// find user by ID
		UserEntity userId = findUser(user);
		// assign bookings to the user
		BookingEntity mappedBookingData = bookingMapper.bookingMapper(bookings, userId, show);
		// save assigned bookings
		BookingEntity bookingStatus = bookingDao.saveBookingsDataToDB(mappedBookingData);
		List<SeatEntity> seats = show.getSeats();
		validateSeatList(seats);
		updateSeats(seats, bookings);
		return bookingStatus;
	}

	private void updateSeats(List<SeatEntity> seats, BookingDTO bookings) {
		
		seats.stream().filter(seat -> seat.getId() == bookings.getSeatId())
		    .forEach(seat -> {byte[][] seatPosition = seat.getSeatPosition();
	         updateSeatPosition(seatPosition, bookings.getCoordinate());
	         updateSeatCounts(seat, bookings.getSeatCount());
	         seatDAO.saveBookings(seat);
	     });


	}

	private ShowEntity getShowById(BookingDTO bookings) {
		return showDAO.findByShowId(bookings.getShowId());
	}

	private UserEntity findUser(Principal user) {

		return userDAO.findUserByEmail(user.getName());
	}

	private void validateSeatList(List<SeatEntity> seats) {
		if (seats == null || seats.isEmpty()) {
			throw new IllegalStateException("No seats found for the show.");
		}
	}

	private void updateSeatPosition(byte[][] seatPosition, String coordinate) {
		String[] split = coordinate.split(",");
		for (int i = 0; i < split.length; i += 2) {
			int row = Integer.parseInt(split[i].trim());
			int column = Integer.parseInt(split[i + 1].trim());
			seatPosition[row][column] = 1;
		}

	}

	private void updateSeatCounts(SeatEntity seat, int seatCount) {
		int reservedSeats = seat.getReservedSeats() + seatCount;
		int availableSeats = seat.getSeats() - seatCount;
		seat.setReservedSeats(reservedSeats);
		seat.setSeats(availableSeats);
	}

	/*
	 * delete Booking
	 */

	public boolean deleteBooking(int seatCount, long seatId, long bookingId, String userEmail) {

		// find the user
		UserEntity userDataObj = userDAO.findUserByEmail(userEmail);

		List<BookingEntity> bookingsByUser = bookingDao.findBookingByUserID(userDataObj);

		for (BookingEntity booking : bookingsByUser) {
			if (bookingId == booking.getBookingId()) {
				List<SeatEntity> seats = booking.getShowId().getSeats();
				for (SeatEntity seat : seats) {

					if (seat.getId() == seatId) {
						unBookTheSeats(seat, seatCount);
						// method call the remove booked seats
						seatDAO.saveBookings(seat);
					}
				}

				// update the booking(remove the cancel booking)
				bookingDao.deleteByBookingsId(bookingId);

				return true;
			}
		}

		return false;
	}

	private void unBookTheSeats(SeatEntity seat, int seatCount) {

		int reservedSeats = seat.getReservedSeats();
		int availableSeats = seat.getSeats();
		seat.setReservedSeats(reservedSeats - seatCount);
		seat.setSeats(availableSeats + seatCount);
		// save the booking
		seatDAO.saveBookings(seat);

	}

}
