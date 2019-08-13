package com.capgemini.services;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.capgemini.dtos.ClienteDTO;

@Service
public class RegisterService {
	
	@Autowired
	RestTemplate restTemplate;
	ClienteDTO clienteDTO;

	public HttpStatus validarRegistro(ClienteDTO clienteDTO) throws URISyntaxException {
		
		final String baseUrl = "http://localhost:9876/cliente";
	    URI uri = new URI(baseUrl);
	 
	    ResponseEntity<HttpStatus> resultado = restTemplate.postForEntity(uri, clienteDTO, HttpStatus.class);
	    
	    HttpStatus status = resultado.getStatusCode();
		
		return status;
	}
	
}
