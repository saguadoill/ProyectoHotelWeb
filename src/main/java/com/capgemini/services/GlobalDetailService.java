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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.dtos.AdminDTO;
import com.capgemini.dtos.ClienteDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GlobalDetailService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserDetails loadUserByUsername(String username) {

		AdminDTO adminUser = new AdminDTO();
		ClienteDTO cliente = new ClienteDTO();
		UserDetails userDetails = null;

		try {
			if (verificarEmail(username)) {
				log.info("Entrando en usuario");

				String baseUrl = "http://localhost:9876/cliente/login/" + username;
				URI uri = new URI(baseUrl);
				cliente = restTemplate.getForObject(uri, ClienteDTO.class);
				GrantedAuthority authority = new SimpleGrantedAuthority(cliente.getRole().toUpperCase());
				userDetails = (UserDetails) new User(cliente.getEmail(), cliente.getPasswd(), Arrays.asList(authority));
			} else {
				log.info("Entrando en admin");
				log.info(username);
				String baseUrl = "http://localhost:9876/admin/" + username;
				URI uri = new URI(baseUrl);
				adminUser = restTemplate.getForObject(uri, AdminDTO.class);
				GrantedAuthority authority = new SimpleGrantedAuthority(adminUser.getRole().toUpperCase());
				userDetails = (UserDetails) new User(adminUser.getNombre(), adminUser.getPasswd(),
						Arrays.asList(authority));

			}
		} catch (URISyntaxException e) {

			e.printStackTrace();
		}

		return userDetails;
	}

	//TODO: comprobar si entra siempre aqui.
	private boolean verificarEmail(String cadena) {
		log.info("validar email:"+cadena);
		boolean esEmail = false;

		for (char c : cadena.toCharArray()) {
			if (c == '@') {
				esEmail = true;
			}
		}

		return esEmail;

	}

}
