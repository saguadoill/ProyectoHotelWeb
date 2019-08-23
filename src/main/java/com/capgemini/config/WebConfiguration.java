package com.capgemini.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	/**
	 * Métod para añadir carpetas al path de recursos
	 *
	 * @param registry - ResourceHandlerRegistry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**", "/css/**", "/js/**", "/includes/**","/admin/**","/calendar/**").addResourceLocations(
				"classpath:/static/images/", 
				"classpath:/static/css/", 
				"classpath:/static/js/",
				"classpath:/static/calendar/", 
				"classpath:/templates/jsp/includes/",
				"classpath:/templates/admin/");
	}
	
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/inicio").setViewName("inicio");
    }

}
