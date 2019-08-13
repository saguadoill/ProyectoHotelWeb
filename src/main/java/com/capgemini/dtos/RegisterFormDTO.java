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
	private String apellidos;
	
	@NotNull
	private String regEmail;
	
	private String regPasswd;
	private String regPasswd2;
	
	private String direccion;
	private String codigoPostal;
	private String ciudad;
}
