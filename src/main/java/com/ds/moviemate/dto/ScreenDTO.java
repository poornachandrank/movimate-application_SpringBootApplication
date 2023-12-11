package com.ds.moviemate.dto;

import org.springframework.stereotype.Component;

import com.ds.moviemate.model.SeatEntity;

import lombok.Data;
@Data
@Component
public class ScreenDTO {

	
	private Long screenId;
	private String screenName;
	private SeatEntity totalSeats;
}
