package com.ds.moviemate.dao;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ds.moviemate.model.ScreenEntity;
import com.ds.moviemate.repository.ScreenRepository;
@Component
public class ScreenDAO {
@Autowired
ScreenRepository screenRepository;
	
	public void deleteByScreenId(Long screenId) {
		screenRepository.deleteById(screenId); 		
	}
	
	public ScreenEntity saveScreen(ScreenEntity screen) {
		return screenRepository.save(screen);
	}

	public List<ScreenEntity> findAllScreen() {
		return screenRepository.findAll();		
	}

	public ScreenEntity findByScreenId(Long screenId) {
		return screenRepository.findById(screenId).orElseThrow(()->new NoSuchElementException("screen not found on that ID"));		
	}

	public ScreenEntity updateScreen(ScreenEntity screen) {
		 return screenRepository.save(screen);		
	}

}
