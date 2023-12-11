package com.ds.moviemate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.model.ShowEntity;

public interface ShowRepository extends JpaRepository<ShowEntity, Long> {

	List<ShowEntity> findByMovieName(MovieEntity movieName);

	@Query(nativeQuery = true, value = "select * from shows where show_date=:DATE AND movie_id=:MOVIEID")
	List<ShowEntity> findByShowDate(@Param("MOVIEID") long movieId, @Param("DATE") String date);

	@Query(nativeQuery = true, value = "select * from shows where cinema_id=:CINEMA AND show_time=:SHOWTIME")
	ShowEntity findShowSeatAvailabiltyforBooking(@Param("CINEMA") long cinema, @Param("SHOWTIME") String showTime);

	@Query(nativeQuery = true, value = "select * from shows where cinema_id=:CINEMA AND show_time=:SHOWTIME AND movie_id=:MOVIENAME")
	List<ShowEntity> findShowSeatAvailabiltyforBookingList(@Param("CINEMA") long cinema,
			@Param("SHOWTIME") String showTime, @Param("MOVIENAME") Long movie);

	List<ShowEntity> findByCinemaName(CinemaEntity cinemaName);

	@Query(nativeQuery = true, value = "select * from shows where cinema_id=:CINEMA AND show_date=:DATE")
	List<ShowEntity> findByCinemaNameAndDate(@Param("CINEMA") Long cinemaId,@Param("DATE") String date);

	
	
}
