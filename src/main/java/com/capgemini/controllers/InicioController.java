package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
		if(principal == null) {
			log.info("Usuario no logado");
		}else {
			log.info("Usuario "+principal.getName()+" logado");
		}
		return model;
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView paginaInicioAdmin(Principal principal,ModelAndView model) {
		model.setViewName("admin");
		
		return model;
	}
	
	@RequestMapping(value = "/carpeta_admin", method = RequestMethod.GET)
	public ModelAndView paginaCarpetaAdmin(Principal principal,ModelAndView model) {
		model.setViewName("carpeta_admin");
		log.info("Admin logado : "+principal.getName());
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
