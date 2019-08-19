package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.ClienteDTO;
import com.capgemini.services.HabitacionService;
import com.capgemini.services.HotelesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class InicioController {
	
	@Autowired
	HotelesService hotelesService;
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public ModelAndView paginaInicio(Principal principal,ModelAndView model) {
		model.setViewName("inicio");
		model.addObject("hoteles", hotelesService.getAll()); 
		
		if(principal == null) {
			model.addObject("usuario", null);
			log.info("Usuario no autenticado");
		}else {
			model.addObject("usuario", principal.getName());
			log.info("Usuario "+principal.getName()+" autenticado");
		}
		return model;
	}
	

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ModelAndView paginaCliente(Principal usuarioLogado, ModelAndView model) {
		model.setViewName("cliente");
		return model;
	}
	
	@RequestMapping(value = "/redirigir", method = RequestMethod.GET)
	public ModelAndView paginaRedireccionarLogin(Principal usuarioLogado, ModelAndView model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		if(hasUserRole) {
			model.setViewName("redirect:/inicio");
		}else {
			model.setViewName("redirect:/admin/inicio");
		}
		return model;
	}
	
	
}
