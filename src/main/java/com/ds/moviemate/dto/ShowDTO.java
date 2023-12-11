package com.ds.moviemate.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ds.moviemate.model.MovieEntity;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.model.SeatEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ShowDTO {

	private String movieTitle;
	private String cinemaName;
	private String showTime;
	private String showDate;
	private List<ScreenEntity> screens;
	private Long showId;
	private MovieEntity movieName;
	private List<SeatEntity> seats;

}
