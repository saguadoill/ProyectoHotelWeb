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

import com.capgemini.services.AdminAuthenticationService;

import lombok.extern.slf4j.Slf4j;

//@Configuration
//@EnableWebSecurity
//@Order(2)
@Slf4j
public class AdminSpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AdminAuthenticationService adminAuthService;

	public AdminSpringSecurityConfig() {
		super();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) {
		try {
			auth.userDetailsService(adminAuthService).passwordEncoder(adminPasswdEncoder());
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

		http.authorizeRequests().antMatchers("/admin").permitAll();

		http.authorizeRequests().antMatchers("/carpeta_admin").hasAuthority("ADMIN");

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

		// Control de sesión ---------------------------
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS).maximumSessions(1)
				.expiredUrl("/admin");

		// Control de sesión ---------------------------
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .maximumSessions(1)
//                .expiredUrl("/login");
	}

	@Bean
	public BCryptPasswordEncoder adminPasswdEncoder() {
		return new BCryptPasswordEncoder();
	}

}
