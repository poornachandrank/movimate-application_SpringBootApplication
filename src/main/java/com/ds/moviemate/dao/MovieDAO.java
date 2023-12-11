package com.ds.moviemate.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.repository.MoviesRepository;

@Component
public class MovieDAO {
@Autowired
MoviesRepository moviesRepository;
	
	public List<MovieEntity> getAllMoviesFromDB() {
		return moviesRepository.findAll();
	}

	public MovieEntity findByMovieTitle(String movieName) {
		return moviesRepository.findByMovieTitle(movieName);	
	}

	public MovieEntity findByMovieID(long movieId) {
		return moviesRepository.findById(movieId).orElseThrow( ()-> new NoSuchElementException("NO MOVIE FOUND ON THIS ID"));
		
	}

	public MovieEntity saveMovie(MovieEntity mapedMovie) {
		return moviesRepository.save(mapedMovie);
	}

	public void deleteByMovieId(Long id) {
		moviesRepository.deleteById(id);
		
	}

}
