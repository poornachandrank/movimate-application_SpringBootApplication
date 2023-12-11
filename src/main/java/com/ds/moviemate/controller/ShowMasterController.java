package com.ds.moviemate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ds.moviemate.dto.ScreenDTO;
import com.ds.moviemate.dto.ShowCreationDTO;
import com.ds.moviemate.dto.ShowDTO;
import com.ds.moviemate.model.ShowEntity;
import com.ds.moviemate.service.serviceimplementaion.CommonServiceImplementaion;
import com.ds.moviemate.service.serviceimplementaion.ShowServiceImplementaion;

@Controller
public class ShowMasterController {

	@Autowired
	CommonServiceImplementaion commonServiceImplementaion;

	@GetMapping("/getDataToCreateShow")
	public ResponseEntity<ShowCreationDTO> showCreateShowPage() {
		ShowCreationDTO collectDataForShowCreation = showServiceImplementaion.collectDataForShowCreation();
		if (collectDataForShowCreation != null) {
			return new ResponseEntity<>(collectDataForShowCreation, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@Autowired
	ShowServiceImplementaion showServiceImplementaion;

	@PostMapping("/SaveCreatedShowtoDB")
	public ResponseEntity<String> saveShow(ShowDTO showDTO) {
		String saveShow = showServiceImplementaion.saveShow(showDTO);
		if ("success".equals(saveShow)) {
			return new ResponseEntity<>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("unsuccess", HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/screendata/{name}")
	public ResponseEntity<List<ScreenDTO>> getAllCinema(@PathVariable(name = "name") String cinemaName) {

		List<ScreenDTO> screensList = showServiceImplementaion.findByCinemaName(cinemaName);

		if (screensList != null) {
			return new ResponseEntity<>(screensList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getAllShow")
	public ResponseEntity<List<ShowDTO>> getAllShow() {
		List<ShowDTO> allShows = showServiceImplementaion.getAllshows();
				return new ResponseEntity<>(allShows, HttpStatus.OK);
		
		}

	


	@PostMapping("/deleteShows/{Id}")
	public String deleteShows(@PathVariable(name = "Id") Long showId) {

		showServiceImplementaion.deleteShow(showId);

		return "redirect:/getAllShows";
	}

	@GetMapping("/getAllShows")
	public ResponseEntity<Model> showUserDetails(Model model) {

		return findPaginated(1, "showId", "asc",5, model);

	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Model> findPaginated(@PathVariable("pageNo") int pageNo,
	        @RequestParam("sortField") String sortField,
	        @RequestParam("sortDir") String sortDir, @RequestParam("pageSize") int pageSize, Model model) {

	    Page<ShowEntity> page = showServiceImplementaion.findPaginated(pageNo, pageSize, sortField, sortDir);
	    List<ShowEntity> listOfShows = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
	    model.addAttribute("userDetails", listOfShows);

	    return new ResponseEntity<>(model, HttpStatus.OK);
	}


}
