package com.capgemini.controllers;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.HotelDTO;
import com.capgemini.services.HotelesService;

@RestController
public class HotelesController {
	
	@Autowired
	HotelesService hotelesService;
	
	@RequestMapping(value = "/hotel/lista", method = RequestMethod.GET)
	public List<HotelDTO> getAllHoteles() throws URISyntaxException {
		
		List<HotelDTO> hoteles = hotelesService.getAll();
		
		return hoteles;
		
	}
	
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET)
	public ModelAndView getHotelById(@PathVariable(value="id") int id, ModelAndView model) {
		
		HotelDTO hotel = hotelesService.getHotel(id);
		model.addObject("hotel", hotel);
		model.setViewName("hotel");
		return model;
		
	}
	
}
