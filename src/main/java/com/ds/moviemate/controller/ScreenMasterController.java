package com.ds.moviemate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.service.serviceimplementaion.ScreenServiceImplementaion;

@Controller
public class ScreenMasterController {

	@Autowired
	public ScreenServiceImplementaion screenServiceImplementaion;

	@PostMapping("/saveNewScreenToDB")
	@ResponseBody
	public ResponseEntity<String> saveNewScreenToDB(ScreenDTO screenEntity) {

		ScreenEntity creenAddStatus = screenServiceImplementaion.addScreen(screenEntity);
		if (creenAddStatus != null) {
			return  new ResponseEntity<>("Success",HttpStatus.OK);

		} else
			return new  ResponseEntity<>("not",HttpStatus.NOT_FOUND);

	}

	@GetMapping("/allScreenData")
	public ResponseEntity<List<ScreenEntity>> showScreenData() {
		List<ScreenEntity> screenList = screenServiceImplementaion.getAllScreen();
		return new ResponseEntity<>(screenList, HttpStatus.OK);

	}

	@DeleteMapping("/deleteScreen/{screenId}")
	public ResponseEntity<String> deleteMovie(@PathVariable(name = "screenId") Long id) {

		boolean screensDeletedStatus = screenServiceImplementaion.deleteScreens(id);

		if (screensDeletedStatus) {

			return new ResponseEntity<>("success", HttpStatus.OK);

		} else {
			return new ResponseEntity<>("Failed to delete movie", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/editScreen/{ID}")
	public ResponseEntity<ScreenEntity> getDataToDelete(@PathVariable(name = "ID") Long id) {

		ScreenEntity screenDataFoundById = screenServiceImplementaion.getScreenDataById(id);
		if (screenDataFoundById != null) {
			return new ResponseEntity<>(screenDataFoundById, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateScreen/{ID}")
	public ResponseEntity<String> updateScreen(@PathVariable(name = "ID") Long id, ScreenDTO updatedData) {

		boolean updateScreenName = screenServiceImplementaion.updateScreenName(id, updatedData);

		if (updateScreenName) {
			return new ResponseEntity<>("Update success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to update", HttpStatus.NOT_FOUND);
		}

	}

	@PostMapping("/save-screens")
	public ResponseEntity<String> saveScreens(@RequestParam("screens") long[] screenIds,
			@RequestParam("seatCounts") Integer[] seatCounts) {
		String screensSavedStatus = screenServiceImplementaion.saveScreens(screenIds, seatCounts);
		if (screensSavedStatus.equals("success")) {
			return ResponseEntity.ok("");

		} else
			return ResponseEntity.ok("not");
		
	}

}
