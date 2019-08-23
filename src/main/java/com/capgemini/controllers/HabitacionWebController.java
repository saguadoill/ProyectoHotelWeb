package com.capgemini.controllers;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.services.HabitacionService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class HabitacionWebController {
	
	@Autowired
	HabitacionService habitacionService;
	
	@RequestMapping(value = "/habitacion/lista", method = RequestMethod.GET)
	public List<HabitacionDTO> listarHabitaciones(ModelAndView model){
		return habitacionService.listarHabitaciones();
	}
	
	@RequestMapping(value = "/habitacion/disponibles", method = RequestMethod.POST)
	public List<HabitacionDTO> findAllHabitacionesDisponiblesHotel(@RequestBody String json){
System.out.println("Json del controlador"+json);
		List<HabitacionDTO> listaHabitaciones = habitacionService.getHabitacionesDisponiblesHotel(json);
		return listaHabitaciones;
		
	}
	
}
