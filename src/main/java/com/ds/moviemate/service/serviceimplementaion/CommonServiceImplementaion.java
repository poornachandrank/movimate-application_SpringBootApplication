package com.ds.moviemate.service.serviceimplementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dao.CinemaDAO;
import com.ds.moviemate.dao.MovieDAO;
import com.ds.moviemate.dao.ShowDAO;
import com.ds.moviemate.dto.ShowCreationDTO;
import com.ds.moviemate.service.CommonService;

@Service
public class CommonServiceImplementaion implements CommonService {

	@Autowired
	CinemaDAO cinemaDAO;
	@Autowired
	MovieDAO movieDAO;
	@Autowired
	ShowDAO showDAO;
	@Override
	public ShowCreationDTO collectDataForShowCreation() {
		return null;
	}
	




}