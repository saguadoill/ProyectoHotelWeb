package com.capgemini.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.capgemini.services.UserAuthenticationService;

import lombok.extern.slf4j.Slf4j;

//@Configuration
//@EnableWebSecurity
//@Order(1)
@Slf4j
public class UserSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserAuthenticationService userAuthService;

	public UserSpringSecurityConfig() {
		super();
	}

	/**
	 * Método para buscar al usuario en la BBDD y setear el password
	 *
	 * @param auth - AuthenticationManagerBuilder
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		log.info("entra a config global");
		try {
			auth.userDetailsService(userAuthService).passwordEncoder(userPasswdEncoder());

		} catch (Exception e) {
			log.error("No se ha podido recuperar el usuario de la BBDD: " + e.getMessage());
		}
	}

	/**
	 * Método para la configuración del acceso
	 *
	 * @param http HttpSecurity
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();

		http.authorizeRequests().antMatchers("/inicio", "/logout", "/403page").permitAll();

		http.authorizeRequests().antMatchers("/cliente", "/cliente/*").hasAuthority("USER");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403page");

		// Configuracion para el formulario de Login
		http.authorizeRequests().and().formLogin()//
				// Configuracion paguina login.
				.loginProcessingUrl("/user_login") // URL del action del formulario
				.loginPage("/inicio")//
				.defaultSuccessUrl("/cliente")//
				.failureUrl("/inicio?error=true")//
				.usernameParameter("email")//
				.passwordParameter("password")
				// Configuracion pagina logout
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/inicio").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true).clearAuthentication(true);

		// Control de sesión ---------------------------
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1)
				.expiredUrl("/inicio");
	}

	@Bean
	public BCryptPasswordEncoder userPasswdEncoder() {
		return new BCryptPasswordEncoder();
	}
}
