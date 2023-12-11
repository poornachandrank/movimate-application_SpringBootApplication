package com.ds.moviemate.service;

import java.util.List;

import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.model.ScreenEntity;

public interface ScreenService {

	
	public ScreenEntity addScreen(ScreenDTO newScreen);
	public List<ScreenEntity> getAllScreen();
}
