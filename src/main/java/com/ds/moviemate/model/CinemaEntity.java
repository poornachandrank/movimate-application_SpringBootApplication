package com.ds.moviemate.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//Cinema entity
@ToString
@Getter
@Setter
@Entity
@Table(name = "cinemas")
public class CinemaEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cinemaId;
	private String cinemaName;
	private String cinemaAddress;
	private int screen;
	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 List<ScreenEntity> screensList;
	
	 

}
