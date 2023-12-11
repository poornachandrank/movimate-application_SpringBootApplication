package com.ds.moviemate.service.serviceimplementaion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ds.moviemate.dao.ScreenDAO;
import com.ds.moviemate.dao.SeatDAO;
import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.manager.ScreenManager;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.service.ScreenService;

@Service
public class ScreenServiceImplementaion implements ScreenService {

	@Autowired
	ScreenDAO screenDAO;
	@Autowired
	SeatDAO seatDAO;
	@Autowired
	ScreenManager screenManager;

	@Override
	public ScreenEntity addScreen(ScreenDTO screenEntity) {
		ScreenEntity screen = new ScreenEntity();
		screen.setScreenName(screenEntity.getScreenName());

		return screenDAO.saveScreen(screen);

	}

	// getAll
	@Override
	public List<ScreenEntity> getAllScreen() {
		return screenDAO.findAllScreen();

	}

	// save
	public String saveScreens(long[] screenIds, Integer[] seatCounts) {
		return screenManager.saveScreen(screenIds, seatCounts);
	}

	// findById
	public ScreenEntity getScreenDataById(Long id) {
		return screenManager.getScreenDataById(id);
	}

	// delete
	public boolean deleteScreens(Long id) {
		return screenManager.deleteScreens(id);
	}

	// update
	public boolean updateScreenName(Long id, ScreenDTO updatedData) {
		return screenManager.updateScreenName(id, updatedData);

	}


}
