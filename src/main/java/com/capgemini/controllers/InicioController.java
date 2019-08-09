package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InicioController {
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView paginaInicio(Principal principal,ModelAndView model) {
		model.setViewName("inicio");
		return model;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView paginaInicioAdmin(Principal principal,ModelAndView model) {
		model.setViewName("admin");
		return model;
	}
	
	@RequestMapping(value = "/403page", method = RequestMethod.GET)
	public ModelAndView paginaErrorInicio(Principal principal,ModelAndView model) {
		model.setViewName("error");
		return model;
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ModelAndView paginaCliente(Principal usuarioLogado, ModelAndView model) {
		model.setViewName("cliente");
		return model;
	}
	
}
