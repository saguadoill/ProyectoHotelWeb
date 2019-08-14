package com.capgemini.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.dtos.ClienteDTO;
import com.capgemini.dtos.HabitacionDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HabitacionService {
	
	@Autowired
	RestTemplate restTemplate;
	
	public List<HabitacionDTO> listarHabitaciones(){
		
		List<HabitacionDTO> habitaciones = new ArrayList<HabitacionDTO>();
		ResponseEntity<List<HabitacionDTO>> listaHabitaciones;
		String baseUrl = "http://localhost:9876/habitacion/lista";
		
		try {
			URI uri = new URI(baseUrl);
			listaHabitaciones = restTemplate.exchange(
		    		 uri,
		    		 HttpMethod.GET,
		    		 null,
		    		 new ParameterizedTypeReference<List<HabitacionDTO>>(){}
		    );
			if(listaHabitaciones.getStatusCode().equals(HttpStatus.OK)) {
				habitaciones = listaHabitaciones.getBody();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return habitaciones;
	}

	public boolean crearHabitacion(HabitacionDTO habitacion) {
		boolean okAdd = false;
		
		
		
		return okAdd;
	}
}
