package com.ds.moviemate.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class MovieDTO {
	
	private Long movieId;
	private String movieTitle;
	private String movieDirector;
	private String movieYear;
	private String movieGenre;
	private String language;
	private String movieRunTime;
	private String movieCast;
	private  MultipartFile movieposter;
	private String fileName ;

}
