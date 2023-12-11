package com.ds.moviemate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ds.moviemate.dto.CinemaDTO;
import com.ds.moviemate.model.CinemaEntity;
import com.ds.moviemate.service.serviceimplementaion.CinemaServiceImplementaion;

@Controller
public class CinemaMasterController {
	@Autowired
	public CinemaServiceImplementaion cinemaServiceImplementaion;

	
	
	
	@PostMapping("/saveNewCinemaToDB")
	@ResponseBody
	public ResponseEntity<String> saveNewCinemaToDB(CinemaDTO newCinema) {
		Boolean cinemaSavedStatus = cinemaServiceImplementaion.saveCinema(newCinema);
		if (Boolean.TRUE.equals(cinemaSavedStatus)) {
			return ResponseEntity.ok("success");
		} else
			return ResponseEntity.ok("not Saved");
	}

	@GetMapping("/AllCinemaData")
	public ResponseEntity<List<CinemaEntity>> showCinemaData() {
		List<CinemaEntity> cinemaList = cinemaServiceImplementaion.getAllCinema();
		if (cinemaList != null) {
			return new ResponseEntity<>(cinemaList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/delete/{Id}")
	public String deleteCinema(@PathVariable(name = "Id") Long cinemaId) {
		cinemaServiceImplementaion.deleteCinemaById(cinemaId);
		return "redirect:/AllCinemaData";
	}

	@GetMapping("/editCinema/{ID}")
	public ResponseEntity<CinemaDTO> editCineam(@PathVariable(name = "ID") Long id) {
		CinemaDTO existingCinema = cinemaServiceImplementaion.getCinemaById(id);
		if (existingCinema != null) {
			return new ResponseEntity<>(existingCinema, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/updateCinema/{cinemaId}")
	public ResponseEntity<String> updateMovie(@PathVariable(name = "cinemaId") Long id, CinemaDTO cinemaEntity) {
		CinemaDTO updateStatus = cinemaServiceImplementaion.updateCinemaScreen(cinemaEntity, id);
		if (updateStatus != null) {
			return new ResponseEntity<>("Update success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failed to update", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/CinemaDetails")
	public ResponseEntity<CinemaDTO> name(@RequestParam(name = "cinemaName") String name) {
		CinemaDTO cinema = cinemaServiceImplementaion.getdataByCinemaName(name);
		if (cinema != null) {
			return new ResponseEntity<>(cinema, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
