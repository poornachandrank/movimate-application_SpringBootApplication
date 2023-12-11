package com.ds.moviemate.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.model.MovieEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShowCreationDTO {

	private List<MovieEntity> movieList;
	private List<CinemaEntity> cinemaList;

}
