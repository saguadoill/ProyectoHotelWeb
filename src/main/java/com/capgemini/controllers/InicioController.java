package com.capgemini.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class InicioController {
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView paginaInicio(ModelAndView model) {
		model.setViewName("index");
		model.addObject("mensaje", "Hola esto funciona");
		return model;
	}

}
