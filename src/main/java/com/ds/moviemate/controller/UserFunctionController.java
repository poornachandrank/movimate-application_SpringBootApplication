package com.ds.moviemate.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ds.moviemate.dto.BookingDTO;
import com.ds.moviemate.dto.CinemaDTO;
import com.ds.moviemate.dto.MovieDTO;
import com.ds.moviemate.dto.PasswordDTO;
import com.ds.moviemate.dto.UserDTO;
import com.ds.moviemate.model.BookingEntity;
import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.model.UserEntity;
import com.ds.moviemate.repository.MoviesRepository;
import com.ds.moviemate.service.serviceimplementaion.BookingServiceImplementation;
import com.ds.moviemate.service.serviceimplementaion.CinemaServiceImplementaion;
import com.ds.moviemate.service.serviceimplementaion.MovieServiceImplementation;
import com.ds.moviemate.service.serviceimplementaion.ShowServiceImplementaion;
import com.ds.moviemate.service.serviceimplementaion.UserServiceImplementaion;

@Controller
public class UserFunctionController {

	@Autowired
	MovieServiceImplementation movieServiceImplementation;
	@Autowired
	ShowServiceImplementaion showServiceImplementaion;
	@Autowired
	MoviesRepository moviesRepository;
	@Autowired
	BookingServiceImplementation bookingServiceImplementation;
	@Autowired
	UserServiceImplementaion userServiceImplementaion;
	@Autowired
	CinemaServiceImplementaion cinemaServiceImplementaion;

	@PostMapping("/Signup")
	public ResponseEntity<String> name(UserDTO newUser) {
		UserEntity newUserSavedStatus = userServiceImplementaion.newUser(newUser);
		if (newUserSavedStatus != null) {
			return new ResponseEntity<>("saved", HttpStatus.OK);
		} else {

			return new ResponseEntity<>("NOT SAVED", HttpStatus.OK);
		}
	}

	@GetMapping("/movies")
	public ModelAndView allmovies(ModelAndView modelAndView) {
		List<MovieEntity> listofMovies = moviesRepository.findAll();
		modelAndView.setViewName("movie/Movie");
		modelAndView.addObject("movieLlist", listofMovies);
		return modelAndView;
	}

	@GetMapping("/User_Home")
	public ModelAndView userHomePage(ModelAndView modelAndView) {
		List<MovieDTO> listOfMovies = movieServiceImplementation.getAllMovie();
		modelAndView.addObject("movies", listOfMovies);
		modelAndView.setViewName("User-logged-page");
		return modelAndView;
	}

	@GetMapping("/BookingShows")
	public ModelAndView userHomePage(@RequestParam("movie") long movieid, ModelAndView modelAndView) {

		List<ShowEntity> showsByMovie = showServiceImplementaion.getShowsByMovieId(movieid);
		modelAndView.addObject("showsBySelectedMovie", showsByMovie);
		modelAndView.setViewName("movie/Shows");
		return modelAndView;
	}

	@GetMapping("/movie")
	public ResponseEntity<Map<String, List<String>>> findShowsWithDateAndMovieName(
			@RequestParam("showDate") String date, @RequestParam("movieName") String movie) {
		Map<String, List<String>> showsByDateAndMovieName = showServiceImplementaion.getShowsByDateAndMovieName(date,
				movie);

		if (showsByDateAndMovieName == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(showsByDateAndMovieName, HttpStatus.OK);

	}

	@GetMapping("/shows")
	public ModelAndView bookMovieName(@RequestParam("cinema") String cinema, @RequestParam("time") String showTime,
			@RequestParam("movie") String movie, @RequestParam("screenId") long screenId, ModelAndView modelAndView) {
		Map<String, Object> findShowSeatAvailabilty = showServiceImplementaion.findShowSeatAvailabilty(cinema, showTime,
				movie, screenId);
		modelAndView.addObject("data", findShowSeatAvailabilty);
		modelAndView.setViewName("movie/Seat");
		return modelAndView;
	}

	@GetMapping("/ticket1")
	public ResponseEntity<BookingEntity> bookingTicket(BookingDTO bookings, Principal user) {
		BookingEntity bookingStatus = bookingServiceImplementation.saveBookings(bookings, user);
		if (bookingStatus != null) {
			return new ResponseEntity<>(bookingStatus, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/account")
	public ModelAndView account(Principal userPrincipal, ModelAndView modelAndView) {
		UserEntity userData = userServiceImplementaion.getUserDataByEmailId(userPrincipal.getName());
		modelAndView.setViewName("UsersPage/MyAccount");
		modelAndView.addObject("userData", userData);
		return modelAndView;

	}

	@PostMapping("/updatePass")
	public ResponseEntity<String> updatePass(PasswordDTO passwordDTO, Principal principal) {

		String updateStatus = userServiceImplementaion.updatePass(passwordDTO, principal);
		if (("success").equals(updateStatus)) {

			return new ResponseEntity<>("Success", HttpStatus.OK);

		}
		return new ResponseEntity<>("UnsuccessFull", HttpStatus.NOT_FOUND);

	}

	@GetMapping("/mybookings")
	public ModelAndView myBookings(Principal userBooking, ModelAndView modelAndView) {
		List<BookingEntity> userBookingData = userServiceImplementaion.getUserBookingData(userBooking);
		modelAndView.addObject("userBookingList", userBookingData);
		modelAndView.setViewName("UsersPage/UserBookedShows");
		return modelAndView;
	}

	@GetMapping("/cancel")
	public String cancelBookings(@RequestParam("seat") int seatCount, @RequestParam("Booking") long bookingId,
			@RequestParam("showId") long showId, Principal principal, @RequestParam("seatId") int seatId) {
		boolean cancelBooking = bookingServiceImplementation.cancelBooking(seatCount, seatId, bookingId,
				principal.getName());
		if (cancelBooking) {
			return "redirect:/mybookings";
		} else {
			return "Bookings can't be cancelled";
		}

	}

	@GetMapping("/cinemas")
	public ModelAndView getallCinemas(ModelAndView modelAndView) {

		List<CinemaEntity> allCinema = cinemaServiceImplementaion.getAllCinema();
		modelAndView.addObject("allCinemas", allCinema);
		modelAndView.setViewName("Theater/theaters");
		return modelAndView;
	}

	@GetMapping("/cinemas/{cinemaId}")
	public ModelAndView getCinema(@PathVariable("cinemaId") long id, ModelAndView modelAndView) {

		CinemaDTO cinemaData = cinemaServiceImplementaion.getCinemaById(id);
		modelAndView.addObject("cinemaData", cinemaData);
		modelAndView.setViewName("Theater/MoviesInCinema");
		return modelAndView;

	}

	@GetMapping("/getAllMovies")
	public ResponseEntity<Object> getMoviesInCinema(@RequestParam("showDate") String date,
			@RequestParam("cinemaName") String cinemaName, ModelAndView modelAndView) {
		Map<String, List<String>> moviesByDateAndCinemaName = showServiceImplementaion
				.getMoviesByDateAndCinemaName(cinemaName, date);

		if (moviesByDateAndCinemaName == null) {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(moviesByDateAndCinemaName, HttpStatus.OK);
	}

}