package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginControllerSS {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView paginaLogin(Principal principal,ModelAndView model) {
		model.setViewName("login");
		
		return model;
	}
	
	@RequestMapping(value = "/403page", method = RequestMethod.GET)
	public ModelAndView paginaErrorInicio(Principal principal,ModelAndView model) {
		model.setViewName("error");
		return model;
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView paginaLogout(Principal principal,ModelAndView model) {
		model.setViewName("inicio");
		
		return model;
	}

}
