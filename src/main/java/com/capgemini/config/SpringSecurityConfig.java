package com.capgemini.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.capgemini.services.UsuarioDetailService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SpringSecurityConfig  extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UsuarioDetailService usuarioDetailService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(usuarioDetailService).passwordEncoder(passwordEncode());
		} catch (Exception e) {
			log.error("No se ha podido recuperar el usuario de la BBDD: " + e.getMessage());
		}
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http.authorizeRequests().antMatchers("/inicio", "/logout", "/403page","/admin").permitAll();
		http.authorizeRequests().antMatchers("/carpeta_admin").hasAuthority("ADMIN");
		http.authorizeRequests().antMatchers("/cliente", "/cliente/*").hasRole("USER");

		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403page");

		// Configuracion para el formulario de Login
		http.authorizeRequests().and().formLogin()//
				// Configuracion paguina login.
				.loginProcessingUrl("/admin_login") // URL del action del formulario
				.loginPage("/admin")//
				.defaultSuccessUrl("/carpeta_admin")//
				.failureUrl("/admin?error=true")//
				.usernameParameter("username")//
				.passwordParameter("password")
				// Configuracion pagina logout
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/admin").deleteCookies("JSESSIONID")
				.invalidateHttpSession(true).clearAuthentication(true);
		
		// Configuracion para el formulario de Login
//		http.authorizeRequests().and().formLogin()//
//				// Configuracion paguina login.
//				.loginProcessingUrl("/user_login") // URL del action del formulario
//				.loginPage("/inicio")//
//				.defaultSuccessUrl("/cliente")//
//				.failureUrl("/inicio?error=true")//
//				.usernameParameter("email")//
//				.passwordParameter("password")
//				// Configuracion pagina logout
//				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/inicio").deleteCookies("JSESSIONID")
//				.invalidateHttpSession(true).clearAuthentication(true);

		// Control de sesión ---------------------------
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1)
				.expiredUrl("/admin");
	}

	@Bean
	public BCryptPasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}

}
