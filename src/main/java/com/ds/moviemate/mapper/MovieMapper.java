package com.ds.moviemate.mapper;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ds.moviemate.dto.MovieDTO;
import com.ds.moviemate.model.MovieEntity;
@Component
public class MovieMapper {
	
	

	public  MovieEntity mapMovie(MovieDTO newMovies) {
		MovieEntity actualMovie = new MovieEntity();
		actualMovie.setMovieTitle(newMovies.getMovieTitle());
		actualMovie.setMovieDirector(newMovies.getMovieDirector());
		actualMovie.setMovieGenre(newMovies.getMovieGenre());
		actualMovie.setMovieRunTime(newMovies.getMovieRunTime());
		actualMovie.setMovieCast(newMovies.getMovieCast());
		actualMovie.setLanguage(newMovies.getLanguage());
		actualMovie.setMovieYear(newMovies.getMovieYear());
		return actualMovie;
	}

	public MovieEntity mapUpdateMovie(MovieDTO updatedMovieData, MovieEntity existingMovie) throws IOException {
		existingMovie.setMovieId(updatedMovieData.getMovieId());
		existingMovie.setMovieTitle(updatedMovieData.getMovieTitle());
		existingMovie.setMovieDirector(updatedMovieData.getMovieDirector());
		existingMovie.setMovieGenre(updatedMovieData.getMovieGenre());
		existingMovie.setMovieCast(updatedMovieData.getMovieCast());
		existingMovie.setMovieRunTime(updatedMovieData.getMovieRunTime());
		existingMovie.setMovieYear(updatedMovieData.getMovieYear());
		existingMovie.setLanguage(updatedMovieData.getLanguage());
		existingMovie.setFileName(Base64.getEncoder().encodeToString(updatedMovieData.getMovieposter().getBytes()));	
	return existingMovie;
	}

	public MovieDTO mapMovieToDTO(MovieEntity movieEntity) {
		MovieDTO movieDTO = new MovieDTO(); 
		movieDTO.setMovieTitle(movieEntity.getMovieTitle());
		movieDTO.setMovieDirector(movieEntity.getMovieDirector());
		movieDTO.setMovieCast(movieEntity.getMovieCast());
		movieDTO.setMovieGenre(movieEntity.getMovieGenre());
		movieDTO.setLanguage(movieEntity.getLanguage());
		movieDTO.setFileName(movieEntity.getFileName());
		movieDTO.setMovieRunTime(movieEntity.getMovieRunTime());
		movieDTO.setMovieYear(movieEntity.getMovieYear());
		movieDTO.setMovieId(movieEntity.getMovieId());

		
		return movieDTO;
	}

	public List<MovieDTO> mapMovieListToDTO(List<MovieEntity> allMovie) {
		
		return allMovie.stream().map(this::convertMovieEntityToDTO).collect(Collectors.toList());

	}


	private MovieDTO convertMovieEntityToDTO(MovieEntity movie) {
		MovieDTO movieDTO = new MovieDTO(); 
		movieDTO.setMovieTitle(movie.getMovieTitle());
		movieDTO.setMovieDirector(movie.getMovieDirector());
		movieDTO.setMovieCast(movie.getMovieCast());
		movieDTO.setMovieGenre(movie.getMovieGenre());
		movieDTO.setLanguage(movie.getLanguage());
		movieDTO.setFileName(movie.getFileName());
		movieDTO.setMovieRunTime(movie.getMovieRunTime());
		movieDTO.setMovieYear(movie.getMovieYear());
		movieDTO.setMovieId(movie.getMovieId());
		return movieDTO;
		
	}




}
