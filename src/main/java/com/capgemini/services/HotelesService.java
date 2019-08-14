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

import com.capgemini.dtos.HotelDTO;

@Service
public class HotelesService {

	@Autowired
	RestTemplate restTemplate;

	public List<HotelDTO> getAll() {

		final String baseUrl = "http://localhost:9876/hotel/lista";
		List<HotelDTO> hoteles = new ArrayList<HotelDTO>();
		URI uri;
		try {
			uri = new URI(baseUrl);
			ResponseEntity<List<HotelDTO>> resultado = restTemplate.exchange(uri, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<HotelDTO>>() {
					});

			if (resultado.getStatusCode().equals(HttpStatus.OK)) {
				hoteles = resultado.getBody();
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hoteles;
	}
}
