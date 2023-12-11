package com.ds.moviemate.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ds.moviemate.model.ScreenEntity;

import lombok.Data;
@Component
@Data
public class CinemaDTO {
	private Long cinemaId;
	private String cinemaName;
	private String cinemaAddress;
	private int screen;
	List<ScreenEntity> screensList;
	

	

}
