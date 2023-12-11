package com.ds.moviemate.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.ds.moviemate.dto.MovieDTO;

public interface MovieService {

	public boolean updateExistingMovie(Long id, MovieDTO updatedMovieData)throws IOException ;
	abstract Boolean saveMovies(MovieDTO newMovies) throws FileNotFoundException;
	public List<MovieDTO> getAllMovie();
	public MovieDTO getMovieById(Long id) ;
	public MovieDTO findByTitle(String movieName);

}
