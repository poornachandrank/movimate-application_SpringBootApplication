package com.ds.moviemate.repositoryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.repository.MoviesRepository;
import com.ds.moviemate.repository.ShowRepository;


@Component
public class CRUDoperation {
	@Autowired
	public ShowRepository showRepository;
	@Autowired
	public MoviesRepository moviesRepository;
	
	public ShowEntity saveShow(ShowEntity newShow) {
		return showRepository.save(newShow);
	}
	
	

}
