package com.ds.moviemate.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.moviemate.dto.MovieDTO;
import com.ds.moviemate.repository.MoviesRepository;
import com.ds.moviemate.service.serviceimplementaion.MovieServiceImplementation;

@Controller
public class MovieMasterController {

	@Autowired
	public MovieServiceImplementation moviesServiceImplementation;

	@Autowired
	public MoviesRepository moviesRepository;

	@PostMapping("/AddMoviestoDB")
	public ResponseEntity<String> addMovieToDB(MovieDTO newMovie) {
		Boolean addMovies = null;
		try {
			 addMovies = moviesServiceImplementation.saveMovies(newMovie);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (Boolean.TRUE.equals(addMovies)) {
			return ResponseEntity.ok("success");

		} else
			return ResponseEntity.ok("Failed");

	}

	@GetMapping("/show-Movies-Data-Table")
	@ResponseBody
	public List<MovieDTO> showMoviesTable() {
		return moviesServiceImplementation.getAllMovie();
	}

	@DeleteMapping("/deleteMovie/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable(name = "movieId") Long id) {
		boolean deleteStatus = moviesServiceImplementation.deleteMovieById(id);

		if (deleteStatus) {

			return new ResponseEntity<>("Movie deleted successfully", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Failed to delete movie", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/editMovie/{ID}")
	public ResponseEntity<MovieDTO> editMovie(@PathVariable(name = "ID") Long id) {
		MovieDTO movieDataToEdit = moviesServiceImplementation.getMovieById(id);
		if (movieDataToEdit != null) {
			return new ResponseEntity<>(movieDataToEdit, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/UpdateMovie/{ID}")
	public ResponseEntity<String> updateMovie(@PathVariable(name = "ID") Long id, MovieDTO updatedMovieData)
			throws IOException {
		if (moviesServiceImplementation.updateExistingMovie(id, updatedMovieData)) {
			return new ResponseEntity<>("update Done", HttpStatus.OK);
		} else
			return new ResponseEntity<>("not Updated", HttpStatus.OK);

	}
}
