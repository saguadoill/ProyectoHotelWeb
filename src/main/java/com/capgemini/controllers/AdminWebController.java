package com.capgemini.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.HabitacionDTO;
import com.capgemini.dtos.HotelDTO;
import com.capgemini.services.HabitacionService;
import com.capgemini.services.HotelesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdminWebController {
	
		@Autowired
		HotelesService hotelesService;
		
		@Autowired
		HabitacionService habitacionService;
	
		@RequestMapping(value = "/admin/inicio", method = RequestMethod.GET)
		public ModelAndView paginaInicioAdmin(Principal adminLogado, ModelAndView model) {
			List<HotelDTO> listAllHoteles = hotelesService.getAll();
			List<HabitacionDTO> listAllHabitaciones = habitacionService.listarHabitaciones();
			model.addObject("listaHoteles", listAllHoteles);
			model.addObject("listaHabitaciones", listAllHabitaciones);
			model.setViewName("admin/inicio_admin");			
			return model;
		}
		
		@RequestMapping(value = "/admin/pruebas", method = RequestMethod.GET)
		public ModelAndView pruebaCalendario(Principal principal, ModelAndView model){
			
			model.setViewName("admin/pruebas");
			
			return model;
			
		}
}
