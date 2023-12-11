package com.ds.moviemate.service.serviceimplementaion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dao.CinemaDAO;
import com.ds.moviemate.dao.ScreenDAO;
import com.ds.moviemate.dto.CinemaDTO;
import com.ds.moviemate.manager.CinemaManager;
import com.ds.moviemate.mapper.CinemaMapper;
import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.service.CinemaService;

@Service
public class CinemaServiceImplementaion implements CinemaService {

	@Autowired
	CinemaDAO cinemaDAO;
	@Autowired
	ScreenDAO screenDAO;
	@Autowired
	CinemaManager cinemaManager;
	@Autowired
	CinemaMapper cinemaMapper;

	@Override

	// saveCinema
	public Boolean saveCinema(CinemaDTO newcinema) {
		return cinemaManager.saveCinema(newcinema);
	}

	// getAllCinemaList
	@Override
	public List<CinemaEntity> getAllCinema() {
   
		return cinemaManager.getAllCinema(); 
	}

	@Override
	// DeleteCinema
	public void deleteCinemaById(Long cinemaId) {
		cinemaManager.deleteCinema(cinemaId);

	}

	@Override
	// getDataByCinemaName
	public CinemaDTO getdataByCinemaName(String name) {
		return  cinemaMapper.mapCinema(cinemaDAO.findByCinemaName(name));
	}

	@Override
	// UpdateCinema
	public CinemaDTO updateCinemaScreen(CinemaDTO cinemaEntity, long id) {
		return cinemaMapper.mapCinema(cinemaManager.updateCinemaScreen(cinemaEntity, id));
	}

	@Override
	// getCinemaDataById
	public CinemaDTO getCinemaById(long id) {
		return cinemaMapper.mapCinema(cinemaDAO.findByCinemaId(id));
				

	}

}
