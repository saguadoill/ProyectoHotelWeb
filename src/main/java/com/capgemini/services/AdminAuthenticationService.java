package com.capgemini.services;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.capgemini.dtos.AdminDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminAuthenticationService implements UserDetailsService {

	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		log.info("Entrando en AdminAuthService");
		AdminDTO adminUser = new AdminDTO();
		UserDetails userDetails = null;
		
		try {
			String baseUrl = "http://localhost:9876/admin/"+username;
			URI uri = new URI(baseUrl);
			adminUser = restTemplate.getForObject(uri,AdminDTO.class);
			GrantedAuthority authority = new SimpleGrantedAuthority(adminUser.getRole().toUpperCase());
			userDetails = (UserDetails)new User(adminUser.getNombre(), 
					adminUser.getPasswd(), Arrays.asList(authority));
		} catch (URISyntaxException e) {
			
			e.printStackTrace();
		}
		return userDetails;
	}

	@Bean
	 public BCryptPasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
}
