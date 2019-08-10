package com.capgemini.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dtos.ClienteDTO;
import com.capgemini.services.RegisterService;

@RestController
public class RegistroController {
	
	@Autowired
	RegisterService registerService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerForm(ModelAndView model, @ModelAttribute(name="registerForm") ClienteDTO clienteDTO, @RequestParam("regPassword") String password) throws URISyntaxException {
		
		System.out.println("hola");
		
		HttpStatus status = registerService.validarRegistro(clienteDTO);
		String respuesta = "";
		
		if (status == HttpStatus.CREATED) {
			respuesta = "ok";
		} else
			respuesta = "ko";
		
		return respuesta;
		
	}
	
}
