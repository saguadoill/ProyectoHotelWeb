package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.services.HotelesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdminWebController {
	
		@Autowired
		HotelesService hotelesService;
	
		@RequestMapping(value = "/admin/inicio", method = RequestMethod.GET)
		public ModelAndView paginaInicioAdmin(Principal adminLogado, ModelAndView model) {
			model.setViewName("admin/inicio_admin");			
			return model;
		}

		@RequestMapping(value = "/admin/hoteles", method = RequestMethod.GET)
		public ModelAndView paginaHotelesAdmin(Principal adminLogado, ModelAndView model) {
			model.setViewName("admin/hoteles_admin");
			model.addObject("lista", hotelesService.getAll());
			
			return model;
		}
}
