package com.capgemini.dtos;

import java.time.LocalDate;

import com.capgemini.LocalDateSerializer;
import com.capgemini.utils.LocalDateDeserializer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

@Data
public class ReservaDTO {
		
	private int id;
	private ClienteDTO clienteDTO;
	private HabitacionDTO habitacionDTO;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate fechaReserva;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate fechaInicio;
	
	@JsonDeserialize(using = LocalDateDeserializer.class)  
	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate fechaFin;
	private Float costeAlojamiento;
	private String estado;
	
}
