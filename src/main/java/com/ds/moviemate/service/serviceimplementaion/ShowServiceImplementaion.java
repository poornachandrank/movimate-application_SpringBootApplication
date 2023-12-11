package com.ds.moviemate.service.serviceimplementaion;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.dto.ShowCreationDTO;
import com.ds.moviemate.dto.ShowDTO;
import com.ds.moviemate.manager.ShowManager;
import com.ds.moviemate.mapper.ShowMapper;
import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.repository.ShowRepository;
import com.ds.moviemate.service.ShowService;

@Service
public class ShowServiceImplementaion implements ShowService {

	@Autowired
	public ShowRepository showRepository;

	@Autowired
	ShowManager showManager;
@Autowired
  ShowMapper showMapper;
	
	// findById
	public List<ShowEntity> getShowsByMovieId(long movieId) {
		return showManager.getShowsByMovieId(movieId);
	}

// findBydate
	public Map<String, List<String>> getShowsByDateAndMovieName(String date, String movie) {

		return showManager.findByDateAndMovieName(date, movie);
	}

	// getSeats
	public Map<String, Object> findShowSeatAvailabilty(String cinema, String showTime, String moviename,
			long screenId) {

		return showManager.findSeat(cinema, showTime, moviename, screenId);
	}

	// save the show
	public String saveShow(ShowDTO showDTO) {

		return showManager.saveShow(showDTO);

	}
	// findByName

	public List<ScreenDTO> findByCinemaName(String cinemaName) {
		return  showMapper.mapshowToDto(showManager.findByCinemaName(cinemaName));


	}

	// findAll
	public List<ShowDTO> getAllshows() {

		return  showMapper.mapshowEntityToDto(showManager.getAllShows());
		
	

	}

	// Delete
	public void deleteShow(long showId) {
		showManager.deleteById(showId);
	}

	public Map<String, List<String>> getMoviesByDateAndCinemaName(String cinemaName, String date) {

		return showManager.getMoviesByDateAndCinemaName(cinemaName, date);
	}

	// getdatabased on page
	public Page<ShowEntity> findPaginated(int pageNo, int pageSize, String sortField, String sortingOrder) {
		Sort sort = sortingOrder.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

		return this.showRepository.findAll(pageable);
	}

	public ShowCreationDTO collectDataForShowCreation() {
		return showManager.collectDataForShowCreation();

	}

	

}
