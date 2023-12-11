package com.ds.moviemate.service.serviceimplementaion;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dto.MovieDTO;
import com.ds.moviemate.manager.MovieManager;
import com.ds.moviemate.mapper.MovieMapper;
import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.service.MovieService;

@Service
public class MovieServiceImplementation implements MovieService {

	@Autowired
	MovieMapper movieMapper;
	@Autowired
	MovieManager movieManager;

	// save
	public Boolean saveMovies(MovieDTO newMovies) throws FileNotFoundException {
		MovieEntity mapedMovie = movieMapper.mapMovie(newMovies);
		return movieManager.save(mapedMovie, newMovies);
	}

	// getallMovies
	@Override
	public List<MovieDTO> getAllMovie() {
		return movieMapper.mapMovieListToDTO(movieManager.getAllMovie());
				
				
	}
	// findByTitle
	public MovieDTO findByTitle(String movieName) {
		return movieMapper.mapMovieToDTO(movieManager.findByMovieTitle(movieName));

	}

	// updateMovie
	@Override
	public boolean updateExistingMovie(Long id, MovieDTO updatedMovieData) throws IOException {

		return movieManager.updateMovie(id, updatedMovieData);

	}

	// getMoviById
	public MovieDTO getMovieById(Long id) {
		return movieMapper.mapMovieToDTO(movieManager.getMoiveById(id));
	}

	// delete movie
	public boolean deleteMovieById(Long id) {
		return movieManager.deleteMovie(id);
	}

}
