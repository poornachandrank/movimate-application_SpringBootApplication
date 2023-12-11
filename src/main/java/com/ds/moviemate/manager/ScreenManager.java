package com.ds.moviemate.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.dao.ScreenDAO;
import com.ds.moviemate.dao.SeatDAO;
import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.model.SeatEntity;



@Component
public class ScreenManager {
	
	@Autowired
	ScreenDAO screenDAO;
	@Autowired
	SeatDAO seatDAO;
	
	
	//saveScreen
	public String saveScreen(long[] screenIds, Integer[] seatCounts) {
		for (int i = 0; i < screenIds.length; i++) {
			Long screenId = screenIds[i];
			Integer seatCount = seatCounts[i];

			SeatEntity newSeatValue = setScreenVData(seatCount);

			SeatEntity save = seatDAO.saveBookings(newSeatValue);

			ScreenEntity screen = screenDAO.findByScreenId(screenId);
			if (screen != null) {

				screen.setTotalSeats(save);

				screenDAO.updateScreen(screen);

			} else {
				return "not success";
			}
		}
		return "success";		
	}
	
	
	// set seat value
	private SeatEntity setScreenVData(int seatCount) {
		SeatEntity newSeatValue = new SeatEntity();
		newSeatValue.setSeats(seatCount);
		newSeatValue.setColunmCount(20);
		newSeatValue.setRowCount(seatCount / 20);
		newSeatValue.setSeatPosition(new byte[seatCount / 20][20]);

		return newSeatValue;
	}


	public ScreenEntity getScreenDataById(Long id) {
		return screenDAO.findByScreenId(id);
	
	}


	public boolean deleteScreens(Long id) {
		boolean deleted = true;
		boolean notDeleted = false;

		ScreenEntity data = screenDAO.findByScreenId(id);

		if (data != null) {
			screenDAO.deleteByScreenId(id);
			return deleted;
		}

		return notDeleted;		
	}


	public boolean updateScreenName(Long id, ScreenDTO updatedData) {

		boolean updateSuccessfull = true;
		boolean notUpdated = false;
		ScreenEntity screenExist = screenDAO.findByScreenId(id);
		screenExist.setScreenName(updatedData.getScreenName());
		ScreenEntity updateStatus = screenDAO.updateScreen(screenExist);
		if (updateStatus != null) {
			return updateSuccessfull;
		}
		return notUpdated;
	
	}
	

}
