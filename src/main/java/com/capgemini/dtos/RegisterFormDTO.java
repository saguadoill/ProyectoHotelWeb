package com.capgemini.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class RegisterFormDTO {
	
	@NotNull
	@Size(min=9, max=9)
	private String dni;
	
	@NotNull
	private String nombre;
	
	@NotNull
	private String apellido;
	
	@NotNull
	private String email;
	
	private String direccion;
	private String codigoPostal;
	private String ciudad;
}
