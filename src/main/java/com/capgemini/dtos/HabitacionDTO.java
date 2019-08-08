package com.capgemini.dtos;

import lombok.Data;

@Data
public class HabitacionDTO {

	private int id;
	private int piso;
	private int numero;
	private String vista;
	private String clase;
	private int camas;
	private float precio;
	private int personas;
	private String estado;
	private HotelDTO hotel; 

}
