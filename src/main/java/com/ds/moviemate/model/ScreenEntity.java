package com.ds.moviemate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Screen entity
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "screens")
public class ScreenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "screenId")
	private Long screenId;
	private String screenName;
    @OneToOne
	private SeatEntity totalSeats;

     
}
