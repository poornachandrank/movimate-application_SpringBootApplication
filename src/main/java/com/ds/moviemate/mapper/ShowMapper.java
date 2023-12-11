package com.ds.moviemate.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.dto.ShowDTO;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.model.ShowEntity;

@Component
public class ShowMapper {
	
	public List<ScreenDTO> mapshowToDto(List<ScreenEntity> list) {
		return list.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private ScreenDTO convertToDTO(ScreenEntity screen) {
		ScreenDTO screenDTO= new ScreenDTO();
		screenDTO.setScreenId(screen.getScreenId());
		screenDTO.setScreenName(screen.getScreenName());
		screenDTO.setTotalSeats(screen.getTotalSeats());
		return screenDTO;
	}

	public List<ShowDTO> mapshowEntityToDto(List<ShowEntity> allShows) {

		return allShows.stream().map(this::convertShowEntityToDTO).collect(Collectors.toList());
	}

	private ShowDTO convertShowEntityToDTO(ShowEntity show) {
		ShowDTO showDTO = new ShowDTO();
		showDTO.setCinemaName(show.getCinemaName().getCinemaName());
		showDTO.setMovieName(show.getMovieName());
		showDTO.setMovieTitle(show.getMovieName().getMovieTitle());
		showDTO.setScreens(show.getScreensList());
		showDTO.setSeats(showDTO.getSeats());
		showDTO.setShowDate(show.getShowDate());
		showDTO.setShowTime(show.getShowTime());
		return showDTO;
	}
}
