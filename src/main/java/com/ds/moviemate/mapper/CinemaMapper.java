package com.ds.moviemate.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ds.moviemate.dto.CinemaDTO;
import com.ds.moviemate.model.CinemaEntity;

@Component
public class CinemaMapper {
	@Autowired
	CinemaDTO cinemaresponse;
	@Autowired
	CinemaDTO cinemaDTO;

	public CinemaEntity mapCinema(CinemaDTO newcinema) {
		CinemaEntity cinemaEntity = new CinemaEntity();
		cinemaEntity.setCinemaName(newcinema.getCinemaName());
		cinemaEntity.setCinemaAddress(newcinema.getCinemaAddress());
		cinemaEntity.setScreen(newcinema.getScreen());
		cinemaEntity.setScreensList(newcinema.getScreensList());
		return cinemaEntity;
	}

	public CinemaDTO mapCinema(CinemaEntity findByCinemaName) {
		cinemaDTO.setCinemaAddress(findByCinemaName.getCinemaAddress());
		cinemaDTO.setCinemaName(findByCinemaName.getCinemaName());
		cinemaDTO.setScreen(findByCinemaName.getScreen());
		cinemaDTO.setScreensList(findByCinemaName.getScreensList());
		cinemaDTO.setCinemaId(findByCinemaName.getCinemaId());
		return cinemaDTO;
	}

	public List<CinemaDTO> mapCinemaList(List<CinemaEntity> allCinemaFromDB) {
		return allCinemaFromDB.stream().map(this::mapListOfCinema).collect(Collectors.toList());

	}
	public CinemaDTO mapListOfCinema(CinemaEntity cinema) {
		cinemaDTO.setCinemaAddress(cinema.getCinemaAddress());
		cinemaDTO.setCinemaName(cinema.getCinemaName());
		cinemaDTO.setScreen(cinema.getScreen());
		cinemaDTO.setScreensList(cinema.getScreensList());
		cinemaDTO.setCinemaId(cinema.getCinemaId());
		
		return cinemaDTO;
	}

}
