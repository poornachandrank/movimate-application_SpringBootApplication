package com.ds.moviemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.CinemaEntity;
@Component
public interface CinemaRepository extends JpaRepository<CinemaEntity, Long> {

	CinemaEntity findByCinemaName(String cinemaName);

}

	
