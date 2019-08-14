package com.capgemini.controllers;

import java.net.URISyntaxException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.ClienteDTO;
import com.capgemini.dtos.RegisterFormDTO;
import com.capgemini.services.RegisterService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class RegistroController {
	
	@Autowired
	RegisterService registerService;
	
	
	@RequestMapping(value = "/registro", method = RequestMethod.GET)
	public ModelAndView paginaRegistro(Principal usuarioLogado, ModelAndView model) {

		ClienteDTO clienteNuevo = new ClienteDTO();
		model.addObject("clienteNuevo",clienteNuevo);
		
		model.setViewName("registro");
		return model;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView registerForm(ModelAndView model, @ModelAttribute ClienteDTO nuevoCliente) throws URISyntaxException {
		
			log.info(nuevoCliente.toString());
		
			String respuesta = "ko";

			HttpStatus status = registerService.validarRegistro(nuevoCliente);
			
			if (status == HttpStatus.CREATED) {
				respuesta = " //pagina intermedia";
			} 
	
		return model;
		
	}
	
}
