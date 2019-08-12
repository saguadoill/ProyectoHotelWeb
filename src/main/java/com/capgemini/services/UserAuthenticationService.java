package com.capgemini.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.dtos.ClienteDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		ClienteDTO cliente = new ClienteDTO();
		UserDetails userDetails = null;
		log.info("Entrando en UserAuthService");
		try {
			String baseUrl = "http://localhost:9876/cliente/login/"+email;
			URI uri = new URI(baseUrl);
			cliente = restTemplate.getForObject(uri,ClienteDTO.class);
		
			GrantedAuthority authority = new SimpleGrantedAuthority(cliente.getRole().toUpperCase());
			userDetails = (UserDetails)new User(cliente.getEmail(), 
					cliente.getPasswd(), Arrays.asList(authority));
			
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		return userDetails;
	}
	
	private boolean verificarEmail(String cadena) {
		boolean esEmail = false;
		
		for (char c : cadena.toCharArray()) {
			if (c == '@') {
				esEmail = true;
			}
		}
		
		return esEmail;
		
	}

}
