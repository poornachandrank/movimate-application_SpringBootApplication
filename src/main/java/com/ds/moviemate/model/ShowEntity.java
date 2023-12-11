package com.ds.moviemate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//MovieShow entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "shows")
public class ShowEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long showId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cinema_id")
	private CinemaEntity cinemaName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	private MovieEntity movieName;

	private String showDate;

	private String showTime;

	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "show_screen", joinColumns = @JoinColumn(name = "showId"), inverseJoinColumns = @JoinColumn(name = "screenId"))
	List<ScreenEntity> screensList;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinTable(name = "show_seat", joinColumns = @JoinColumn(name = "showId"), inverseJoinColumns = @JoinColumn(name = "seatId"))
	private List<SeatEntity> seats;
}
