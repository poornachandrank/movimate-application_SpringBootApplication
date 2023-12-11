package com.ds.moviemate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.MovieEntity;
@Component
public interface MoviesRepository extends JpaRepository<MovieEntity, Long> {

	 MovieEntity findByMovieTitle(String movieName);
}
