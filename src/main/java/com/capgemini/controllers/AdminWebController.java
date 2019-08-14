package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdminWebController {
	
		@RequestMapping(value = "/admin/inicio", method = RequestMethod.GET)
		public ModelAndView paginaInicioAdmin(Principal usuarioLogado, ModelAndView model) {
			model.setViewName("admin/inicio_admin");			
			return model;
		}

}
