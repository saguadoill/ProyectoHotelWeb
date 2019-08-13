package com.capgemini.controllers;

import java.net.URISyntaxException;

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

@RestController
public class RegistroController {
	
	@Autowired
	RegisterService registerService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerForm(ModelAndView model, @ModelAttribute(name="registerForm") RegisterFormDTO registerForm) throws URISyntaxException {
		
			String respuesta = "ko";
		
		if (registerForm.getRegPasswd().equals(registerForm.getRegPasswd2())) {
			
			ClienteDTO clienteDTO = new ClienteDTO();
			
			clienteDTO.setNombre(registerForm.getNombre());
			clienteDTO.setApellido(registerForm.getApellidos());
			clienteDTO.setDni(registerForm.getDni());
			clienteDTO.setEmail(registerForm.getRegEmail());
			clienteDTO.setPasswd(registerForm.getRegPasswd());
			
			if (registerForm.getCiudad() != null) {
				clienteDTO.setCiudad(registerForm.getCiudad());
			}
			
			if (registerForm.getDireccion() != null) {
				clienteDTO.setDireccion(registerForm.getDireccion());
			}
			
			if (registerForm.getCodigoPostal() != null) {
				clienteDTO.setCodigoPostal(registerForm.getCodigoPostal());
			}
			
			HttpStatus status = registerService.validarRegistro(clienteDTO);
			
			if (status == HttpStatus.CREATED) {
				respuesta = "ok";
			} 
		}
	
		return respuesta;
		
	}
	
}
