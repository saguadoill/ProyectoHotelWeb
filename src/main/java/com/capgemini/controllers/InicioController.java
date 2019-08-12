package com.capgemini.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	
//	@RequestMapping(value = "/admin", method = RequestMethod.GET)
//	public ModelAndView paginaInicioAdmin(Principal principal,ModelAndView model) {
//		model.setViewName("admin/home");
//		
//		return model;
//	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView paginaLogin(Principal principal,ModelAndView model) {
		model.setViewName("login");
		
		return model;
	}

	
//	@RequestMapping(value = "/carpeta_admin", method = RequestMethod.GET)
//	public ModelAndView paginaCarpetaAdmin(Principal principal,ModelAndView model) {
//		model.setViewName("carpeta_admin");
//		log.info("Admin logado : "+principal.getName());
//		return model;
//	}
	
	@RequestMapping(value = "/403page", method = RequestMethod.GET)
	public ModelAndView paginaErrorInicio(Principal principal,ModelAndView model) {
		model.setViewName("acceso_denegado");
		return model;
	}

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public ModelAndView paginaCliente(Principal usuarioLogado, ModelAndView model) {
		model.setViewName("cliente");
		return model;
	}
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public ModelAndView paginaRegistro(Principal usuarioLogado, ModelAndView model) {
		model.setViewName("registro");
		return model;
	}
	
	@RequestMapping(value = "/addregistro", method = RequestMethod.GET)
	public ModelAndView paginaAddRegistro(Principal usuarioLogado, ModelAndView model) {
	
		return model;
	}
	
	
	@RequestMapping(value = "/redirigir", method = RequestMethod.GET)
	public ModelAndView paginaRedireccionarLogin(Principal usuarioLogado, ModelAndView model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		boolean hasUserRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_USER"));
		if(hasUserRole) {
			model.setViewName("redirect:cliente");
		}else {
			model.setViewName("redirect:/admin/inicio");
		}
		return model;
	}
	
	@RequestMapping(value = "/admin/inicio", method = RequestMethod.GET)
	public ModelAndView paginaInicioAdmin(Principal usuarioLogado, ModelAndView model) {
		model.setViewName("admin/inicio_admin");
		return model;
	}
}
