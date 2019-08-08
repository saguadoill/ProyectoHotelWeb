package com.capgemini.dtos;

import java.util.List;

import lombok.Data;

@Data
public class HotelDTO {
	
	private int id;
	private String nombre;
	private String categoria;
	private String zona;
	private String direccion;
	private List<HabitacionDTO> listaHabitaciones;
	
}
