package com.ds.moviemate.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "seats")
public class SeatEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "total_seat")
	private int seats;
	private int reservedSeats;
	@ManyToMany
	private List<ScreenEntity> screenId;
	private int colunmCount;
	private int rowCount;
	@Lob
	@Column(name = "seat_position")
	private byte[][] seatPosition;
private String screenName;
}
