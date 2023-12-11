package com.ds.moviemate.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.repository.CinemaRepository;
@Component
public class CinemaDAO {

	@Autowired
	CinemaRepository cinemaRepository;

	public void deleteCinemaById(Long cinemaId) {

		cinemaRepository.deleteById(cinemaId);

	}

	public CinemaEntity savecinema(CinemaEntity cinemaEntity) {
		return cinemaRepository.save(cinemaEntity);
	}

	public List<CinemaEntity> getAllCinemaFromDB() {

		return cinemaRepository.findAll();
	}

	public CinemaEntity findByCinemaName(String cinemaName) {
		return cinemaRepository.findByCinemaName(cinemaName);
	}
	
	public CinemaEntity findByCinemaId(long cinemaid) {
		return cinemaRepository.findById(cinemaid).orElseThrow(() -> new NoSuchElementException("No cinema found with ID: " + cinemaid));
	}

	
}