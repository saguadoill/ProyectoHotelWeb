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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.dtos.ClienteDTO;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticationUserService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		ClienteDTO cliente = new ClienteDTO();
		UserDetails userDetails = null;
		
		try {
			String baseUrl = "http://localhost:9876/cliente/login/"+email;
			URI uri = new URI(baseUrl);
			cliente = restTemplate.getForObject(uri,ClienteDTO.class);
		
			GrantedAuthority authority = new SimpleGrantedAuthority(cliente.getRole());
			userDetails = (UserDetails)new User(cliente.getEmail(), 
					cliente.getPasswd(), Arrays.asList(authority));
			
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		return userDetails;
	}

}
