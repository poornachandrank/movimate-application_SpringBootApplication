package com.ds.moviemate.service;

import java.util.List;
import java.util.Map;

import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.dto.ShowDTO;

public interface ShowService {
	public Map<String, List<String>> getMoviesByDateAndCinemaName(String cinemaName, String date);
	public void deleteShow(long showId);
	public List<ShowDTO> getAllshows();
	public String saveShow(ShowDTO showDTO) ;
	public List<ScreenDTO>findByCinemaName(String cinemaName) ;
	public Map<String, List<String>> getShowsByDateAndMovieName(String date, String movie) ;
}
