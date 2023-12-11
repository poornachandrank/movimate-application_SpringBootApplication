package com.ds.moviemate.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.repository.ShowRepository;
@Component
public class ShowDAO {
	
	@Autowired
	public ShowRepository showRepository;
	
	
	public ShowEntity findByShowId(long showEntity) {
		
		return showRepository.findById(showEntity)
				.orElseThrow(() -> 
				new NoSuchElementException("No show found with ID: " + showEntity));
		
		
	}


	public ShowEntity saveNewShow(ShowEntity newShowSavedStatus) {
		return showRepository.save(newShowSavedStatus);		
	}
	
	
	public ShowEntity saveShow(ShowEntity newShow) {
		return showRepository.save(newShow);
		
	}


	public List<ShowEntity> findByMovieName(MovieEntity movieEntity) {
		
		return showRepository.findByMovieName(movieEntity);
	}


	public List<ShowEntity> findByShowDate(Long movieId, String date) {
		return 	showRepository.findByShowDate(movieId, date);
		
	}


	public List<ShowEntity> findShowSeatAvailabiltyforBookingList(long cinemaid, String showTime, Long movieId) {
		return  showRepository
				.findShowSeatAvailabiltyforBookingList(cinemaid, showTime, movieId);
	}


	public List<ShowEntity> findAllShows() {
		return showRepository.findAll();		
	}


	public void deleteShowById(long showId) {
		showRepository.deleteById(showId);		
	}


	public List<ShowEntity> findByCinemaNameAndDate(Long cinemaId, String date) {
		return showRepository.findByCinemaNameAndDate(cinemaId,date);		
	}
}
