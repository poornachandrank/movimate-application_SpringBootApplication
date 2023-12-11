package com.ds.moviemate.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.dao.CinemaDAO;
import com.ds.moviemate.dao.MovieDAO;
import com.ds.moviemate.dao.SeatDAO;
import com.ds.moviemate.dao.ShowDAO;
import com.ds.moviemate.dto.ShowCreationDTO;
import com.ds.moviemate.dto.ShowDTO;
import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.model.SeatEntity;
import com.ds.moviemate.model.ShowEntity;



@Component
public class ShowManager {

	@Autowired
	SeatDAO seatDAO;
	@Autowired
	ShowDAO showDAO;
	@Autowired
	MovieDAO movieDAO;
	@Autowired
	CinemaDAO cinemaDAO;
	@Autowired
	ShowCreationDTO showCreationDTO;

	public List<ShowEntity> getShowsByMovieId(long movieId) {
		MovieEntity data = movieDAO.findByMovieID(movieId);

		if (data != null) {
			return showDAO.findByMovieName(data);
		}
		return Collections.emptyList();
	}

	public Map<String, List<String>> findByDateAndMovieName(String date, String movie) {
		MovieEntity movieobj = movieDAO.findByMovieTitle(movie);
		Long movieId = movieobj.getMovieId();
		List<ShowEntity> showDetailsWithMentionedDateAndMovieName = showDAO.findByShowDate(movieId, date);
		Map<String, List<String>> showDetails = new HashMap<>();
		return getCinemaAndShowTimeData(showDetailsWithMentionedDateAndMovieName, showDetails);

	}
	private Map<String, List<String>> getCinemaAndShowTimeData(
			List<ShowEntity> showDetailsWithMentionedDateAndMovieName, Map<String, List<String>> showDetails) {
		for (int i = 0; i < showDetailsWithMentionedDateAndMovieName.size(); i++) {
			String cinemaName = showDetailsWithMentionedDateAndMovieName.get(i).getCinemaName().getCinemaName();
			String showTime = showDetailsWithMentionedDateAndMovieName.get(i).getShowTime();
			showDetails.computeIfAbsent(cinemaName, key -> new ArrayList<>());
			List<ScreenEntity> screensList = showDetailsWithMentionedDateAndMovieName.get(i).getScreensList();
			for (int j = 0; j < screensList.size(); j++) {
				showDetails.get(cinemaName).add(showTime);
				showDetails.get(cinemaName).add(screensList.get(j).getScreenName());
				showDetails.get(cinemaName).add(screensList.get(j).getScreenId().toString());
			}
		}

		return showDetails;
	}

	public Map<String, Object> findSeat(String cinema, String showTime, String moviename, long screenId) {
	Map<String, Object> data = new HashMap<>();
		
		CinemaEntity cinemaobj = cinemaDAO.findByCinemaName(cinema);
		
		long cinemaid = cinemaobj.getCinemaId();
		
		MovieEntity movieObj = movieDAO.findByMovieTitle(moviename);
		Long movieId = movieObj.getMovieId();
		List<SeatEntity> seats;
		List<ShowEntity> findShowSeatAvailabiltyforBooking = showDAO.findShowSeatAvailabiltyforBookingList(cinemaid,
				showTime, movieId);
		ShowEntity show = new ShowEntity();
		ScreenEntity screenEntity = new ScreenEntity();
		SeatEntity seatentity = new SeatEntity();
		for (int i = 0; i < findShowSeatAvailabiltyforBooking.size(); i++) {
			seats = findShowSeatAvailabiltyforBooking.get(i).getSeats();
			for (int j = 0; j < seats.size(); j++) {
				show = findShowSeatAvailabiltyforBooking.get(i);
				if (seats.get(j).getScreenId().get(j).getScreenId() == screenId) {

					show = findShowSeatAvailabiltyforBooking.get(i);
					seatentity = seats.get(j);
					screenEntity = seats.get(j).getScreenId().get(j);

				}
			}

		}
		data.put("showObj", show);
		data.put("screenObj", screenEntity);
		data.put("seatobj", seatentity);
		return data;
	}

	public String saveShow(ShowDTO showDTO) {
		ShowEntity newShow = createShowEntity(showDTO);
		ShowEntity savedShow = showDAO.saveShow(newShow);
		List<SeatEntity> seatsList = createAndSaveSeatEntities(showDTO);
		savedShow.setSeats(seatsList);
		showDAO.saveShow(newShow);

		return "success";	
	}		
	
	
	private ShowEntity createShowEntity(ShowDTO showDTO) {
		ShowEntity newShow = new ShowEntity();

		newShow.setCinemaName(cinemaDAO.findByCinemaName(showDTO.getCinemaName()));

		newShow.setMovieName(movieDAO.findByMovieTitle(showDTO.getMovieTitle()));

		String formattedDate = formatDate(showDTO.getShowDate());
		newShow.setShowDate(formattedDate);

		newShow.setShowTime(showDTO.getShowTime());
		newShow.setScreensList(showDTO.getScreens());

		return newShow;
	}

	private String formatDate(String date) {
		String[] charArray = date.split("-");
		StringBuilder formattedArray = new StringBuilder();
		for (int i = charArray.length - 1; i >= 0; i--) {
			formattedArray.append(charArray[i]);
			if (i >= 1) {
				formattedArray.append("-");
			}
		}
		return formattedArray.toString();
	}

	private List<SeatEntity> createAndSaveSeatEntities(ShowDTO showDTO) {
		List<SeatEntity> seatsList = new ArrayList<>();
		List<ScreenEntity> screens = showDTO.getScreens();
		for (ScreenEntity screen : screens) {
			int seats = screen.getTotalSeats().getSeats();
			int colunmCount = screen.getTotalSeats().getColunmCount();
			int rowCount = screen.getTotalSeats().getRowCount();
			byte[][] seatPosition = screen.getTotalSeats().getSeatPosition();
			SeatEntity showSeatwithData = setDataToSet(seats, colunmCount, rowCount, seatPosition, screen, showDTO);

			SeatEntity savedShowSeat = seatDAO.saveBookings(showSeatwithData);
			seatsList.add(savedShowSeat);
		}
		return seatsList;
	}

	// create seatEntity object and set value and return
	private SeatEntity setDataToSet(int seats, int colunmCount, int rowCount, byte[][] seatPosition,
			ScreenEntity screen, ShowDTO showDTO) {
		SeatEntity showSeat = new SeatEntity();
		showSeat.setSeats(seats);
		showSeat.setColunmCount(colunmCount);
		showSeat.setRowCount(rowCount);
		showSeat.setSeatPosition(seatPosition);
		showSeat.setScreenId(showDTO.getScreens());
		showSeat.setScreenName(screen.getScreenName());
		return showSeat;
	}

	public List<ScreenEntity> findByCinemaName(String cinemaName) {
		CinemaEntity findByCinemaName = cinemaDAO.findByCinemaName(cinemaName);

		return findByCinemaName.getScreensList();		
	}

	public List<ShowEntity> getAllShows() {
		return showDAO.findAllShows();	}

	public void deleteById(long showId) {
		showDAO.deleteShowById(showId);

	}

	public Map<String, List<String>> getMoviesByDateAndCinemaName(String cinemaName, String date) {
		CinemaEntity cinemaObj = cinemaDAO.findByCinemaName(cinemaName);
		Long cinemaId = cinemaObj.getCinemaId();

		List<ShowEntity> showDetailsWithMentionedDateAndCinemaName = showDAO.findByCinemaNameAndDate(cinemaId, date);
		Map<String, List<String>> showDetails = new HashMap<>();
		for (int i = 0; i < showDetailsWithMentionedDateAndCinemaName.size(); i++) {
			String movieName = showDetailsWithMentionedDateAndCinemaName.get(i).getMovieName().getMovieTitle();
			String showTime = showDetailsWithMentionedDateAndCinemaName.get(i).getShowTime();
			if (!showDetails.containsKey(movieName)) {
				showDetails.put(movieName, new ArrayList<>());
			}
			List<ScreenEntity> screensList = showDetailsWithMentionedDateAndCinemaName.get(i).getScreensList();
			for (int j = 0; j < screensList.size(); j++) {

				addMovieDetatils(movieName, showTime, screensList, showDetails, j);

			}

		}
		return showDetails;

	}

	// add data to map
	private Map<String, List<String>> addMovieDetatils(String movieName, String showTime,
			List<ScreenEntity> screensList, Map<String, List<String>> showDetails, int j) {
		showDetails.get(movieName).add(showTime);
		showDetails.get(movieName).add(screensList.get(j).getScreenName());
		showDetails.get(movieName).add(screensList.get(j).getScreenId().toString());

		return showDetails;
	}

	public ShowCreationDTO collectDataForShowCreation() {
		List<CinemaEntity> cinemaDataList = cinemaDAO.getAllCinemaFromDB();
		List<MovieEntity> moviesDataList = movieDAO.getAllMoviesFromDB();
		showCreationDTO.setMovieList(moviesDataList);
		showCreationDTO.setCinemaList(cinemaDataList);
		return showCreationDTO;		
	}		
	}


	


