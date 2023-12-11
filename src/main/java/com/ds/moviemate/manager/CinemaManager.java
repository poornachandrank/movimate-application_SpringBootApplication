package com.ds.moviemate.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.dao.CinemaDAO;
import com.ds.moviemate.dao.ScreenDAO;
import com.ds.moviemate.dto.CinemaDTO;
import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.mapper.CinemaMapper;
import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.service.serviceimplementaion.ScreenServiceImplementaion;
@Component
public class CinemaManager {

	@Autowired
	ScreenDAO screenDAO;
	@Autowired
	CinemaDAO cinemaDAO;
	@Autowired
	public ScreenServiceImplementaion screenServiceImplementaion;
@Autowired
CinemaMapper cinemaMapper;

	public Boolean saveCinema(CinemaDTO newcinema) {
		 
		int totalScreen = newcinema.getScreen();
		List<ScreenEntity> screenList = createScreen(totalScreen);
		newcinema.setScreensList(screenList);
		CinemaEntity mappedCinema = cinemaMapper.mapCinema(newcinema);

		
		CinemaEntity savedCinemaObj = cinemaDAO.savecinema(mappedCinema);
		
	
		// add screen to cinema
	
	
		// save updated Cinema with screen
		CinemaEntity saved = cinemaDAO.savecinema(savedCinemaObj);
		Predicate<CinemaEntity> cinemaSavedStatus = Objects::nonNull;
		return cinemaSavedStatus.test(saved);

	}

	private List<ScreenEntity> createScreen(int totalScreen) {
		List<ScreenEntity> screenList = new ArrayList<>();
		for (int i = 1; i <= totalScreen; i++) {
			ScreenDTO screen = new ScreenDTO();
			screen.setScreenName("Screen " + i);
			ScreenEntity saveScreenObj = screenServiceImplementaion.addScreen(screen);
			screenList.add(saveScreenObj);
		}
		return screenList;
	}



	public void deleteCinema(Long cinemaId) {
		cinemaDAO.deleteCinemaById(cinemaId);

	}

	public CinemaEntity updateCinemaScreen(CinemaDTO cinemaEntity, long id) {

		CinemaEntity existingCinema = cinemaDAO.findByCinemaId(id);
		List<ScreenEntity> screensList = existingCinema.getScreensList();
		if (screensList.size() >= cinemaEntity.getScreen()) {
			return removeScreen(cinemaEntity, id);
		}

		else {

			return addNewScreen(cinemaEntity, id);

		}

	}

	private CinemaEntity removeScreen(CinemaDTO cinemaEntity, long cinemaid) {
		CinemaEntity existingCinema = cinemaDAO.findByCinemaId(cinemaid);
		List<ScreenEntity> screensList = existingCinema.getScreensList();
		for (int i = screensList.size() - 1; i >= 0; i--) {
			String screenName = screensList.get(i).getScreenName();
			int numericValue = Character.getNumericValue(screenName.charAt(7));
			if (numericValue > cinemaEntity.getScreen()) {
				Long screenId = screensList.get(i).getScreenId();
				screensList.remove(i);
				// delete screen based on cinema
				screenDAO.deleteByScreenId(screenId);
			}
		}

		return setScreenData(existingCinema, cinemaEntity, screensList);
	}

	private CinemaEntity addNewScreen(CinemaDTO cinemaEntity, long id) {
		// findCinema
		CinemaEntity existingCinema = cinemaDAO.findByCinemaId(id);

		List<ScreenEntity> screensList = existingCinema.getScreensList();
		int newScreenCount = cinemaEntity.getScreen();
		int oldScreenCount = existingCinema.getScreen();
		int newCount = (newScreenCount - oldScreenCount) + 3;
		for (int j = oldScreenCount + 1; j < newCount; j++) {
			ScreenDTO screen = new ScreenDTO();
			screen.setScreenName("Screen " + j);
			ScreenEntity saveScreenObj = screenServiceImplementaion.addScreen(screen);
			screensList.add(saveScreenObj);
		}
		return setScreenData(existingCinema, cinemaEntity, screensList);
	}

	private CinemaEntity setScreenData(CinemaEntity existingCinema, CinemaDTO cinemaEntity,
			List<ScreenEntity> screensList) {
		existingCinema.setCinemaName(cinemaEntity.getCinemaName());
		existingCinema.setCinemaAddress(cinemaEntity.getCinemaAddress());
		existingCinema.setScreen(cinemaEntity.getScreen());
		existingCinema.setScreensList(screensList);
		return cinemaDAO.savecinema(existingCinema);
	}

	public List<CinemaEntity> getAllCinema() {
	return 	cinemaDAO.getAllCinemaFromDB();		
	}

}
