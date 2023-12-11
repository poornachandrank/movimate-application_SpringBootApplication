package com.ds.moviemate.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long bookingId;
	@ManyToOne
	private ShowEntity showId;
	@ManyToOne(fetch = FetchType.EAGER)
	private UserEntity userId;
	private int seatCount;
	private double totalPrice;
	private String seatPosition ;
	private String seatName;
	private String paymentStatus= "PAIED";
	private String bookingStatus= "SUCCESS";
	@CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP ", nullable = false, updatable = false)
    private LocalDateTime creationtime;
	

}
