package com.ds.moviemate.service;

import java.util.List;

import com.ds.moviemate.dto.CinemaDTO;
import com.ds.moviemate.model.CinemaEntity;

public interface CinemaService {

	public List<CinemaEntity> getAllCinema();

	public Boolean saveCinema(CinemaDTO newcinema);

	public void deleteCinemaById(Long cinemaId);

	public CinemaDTO getdataByCinemaName(String name);

	public CinemaDTO updateCinemaScreen(CinemaDTO cinemaEntity, long id);

	public CinemaDTO getCinemaById(long id);

}
