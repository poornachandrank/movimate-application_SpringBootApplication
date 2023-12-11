package com.ds.moviemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ds.moviemate.model.SeatEntity;
public interface SeatRepository extends JpaRepository<SeatEntity, Long> {

	
}
