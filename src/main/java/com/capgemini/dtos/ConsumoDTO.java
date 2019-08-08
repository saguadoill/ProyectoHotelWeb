package com.capgemini.dtos;

import lombok.Data;

@Data
public class ConsumoDTO {
	
	private int id;
	private ReservaDTO reserva;
	private ProductoDTO producto;
	private int cantidad;
	private float precio;
}
