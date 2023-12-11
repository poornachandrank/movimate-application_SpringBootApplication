package com.ds.moviemate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//Movie entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movies")
public class MovieEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long movieId;
	private String movieTitle;
	private String movieDirector;
	private String movieYear;
	private String movieGenre;
	private String language;
	private String movieRunTime;
	private String movieCast;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String fileName ;
	

}
