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

import com.capgemini.services.GlobalUserDetailService;


@Configuration
@EnableWebSecurity
public class GlobalSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	GlobalUserDetailService usuarioAuth;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.cors().and().csrf().disable();
		httpSecurity.authorizeRequests().antMatchers("/inicio","/login", "/admin", "/403page","/registro").permitAll();
		httpSecurity.authorizeRequests().antMatchers("/admin/*").hasAuthority("ADMIN");
		httpSecurity.authorizeRequests().antMatchers("/cliente").hasRole("USER");
		
		httpSecurity.formLogin().loginPage("/login")
					.loginProcessingUrl("/userLogin")
					.defaultSuccessUrl("/redirigir")
					.failureUrl("/login?error=true")
					.usernameParameter("username").passwordParameter("password")
					.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/inicio")
					.deleteCookies("JSESSIONID")
					.and()
					.exceptionHandling().accessDeniedPage("/403page");
		
		 // Control de sesión ---------------------------
		httpSecurity.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .maximumSessions(1)
                .expiredUrl("/login");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(usuarioAuth).passwordEncoder(encoder());
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}