package com.ds.moviemate.dto;

import java.time.LocalDateTime;

import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.model.UserEntity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BookingDTO {
	private int seatCount;
	private long showId;
	private ShowEntity showIds;
	private long seatId;
	private double totalPrice;
	private String seatName;
	private String coordinate;
	private String[] clickedCheckboxes;
	private long bookingId;
	private UserEntity userId;
	private String seatPosition;
	private String paymentStatus ;
	private String bookingStatus ;
	private LocalDateTime creationtime;
}