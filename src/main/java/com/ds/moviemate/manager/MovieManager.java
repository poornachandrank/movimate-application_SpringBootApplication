package com.ds.moviemate.manager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.ds.moviemate.dao.MovieDAO;
import com.ds.moviemate.dto.MovieDTO;
import com.ds.moviemate.mapper.MovieMapper;
import com.ds.moviemate.model.MovieEntity;

@Component
public class MovieManager {

	@Autowired
	public MovieDAO movieDAO;
	@Autowired
	public MovieMapper movieMapper;

	public boolean save(MovieEntity mapedMovie, MovieDTO newMovies) throws FileNotFoundException {

		String fileName = StringUtils.cleanPath(newMovies.getMovieposter().getOriginalFilename());
		if (fileName.contains("..")) {
			throw new FileNotFoundException();
		}
		try {
			mapedMovie.setFileName(Base64.getEncoder().encodeToString(newMovies.getMovieposter().getBytes()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		MovieEntity save = movieDAO.saveMovie(mapedMovie);
		Predicate<MovieEntity> saveStatus = status -> (status != null);

		return saveStatus.test(save);
	}

	//updateMovie
	public boolean updateMovie(Long id, MovieDTO updatedMovieData) throws IOException {

		MovieEntity existingMovie = movieDAO.findByMovieID(id);

		MovieEntity updatedMovie = movieMapper.mapUpdateMovie(updatedMovieData, existingMovie);

		movieDAO.saveMovie(updatedMovie);
		return true;
	}

	// delete
	public boolean deleteMovie(Long id) {
		boolean movieDeleted = true;
		boolean notDeleted = false;

		MovieEntity moviedata = movieDAO.findByMovieID(id);
		if (moviedata != null) {

			movieDAO.deleteByMovieId(id);
			return movieDeleted;
		}

		return notDeleted;
	}

	public MovieEntity getMoiveById(Long id) {
		return movieDAO.findByMovieID(id);

	}

	public MovieEntity findByMovieTitle(String movieName) {
		return movieDAO.findByMovieTitle(movieName);

	}

	public List<MovieEntity> getAllMovie() {
		return movieDAO.getAllMoviesFromDB();
	}

}
