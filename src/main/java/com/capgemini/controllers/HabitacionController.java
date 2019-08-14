package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.services.HabitacionService;

@RestController
public class HabitacionController {
	
	@Autowired
	HabitacionService habitacionService;
	
	@RequestMapping(value = "/habitacion/lista", method = RequestMethod.GET)
	public List<HabitacionDTO> listarHabitaciones(ModelAndView model){
		return habitacionService.listarHabitaciones();
	}
	
	

}
