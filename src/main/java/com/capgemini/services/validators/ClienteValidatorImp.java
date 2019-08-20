package com.capgemini.services.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.capgemini.dtos.ClienteDTO;
import com.capgemini.services.validators.impls.IClienteValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClienteValidatorImp implements ConstraintValidator<IClienteValidator, ClienteDTO> {
	
	
	@Override
	public boolean isValid(ClienteDTO cliente, ConstraintValidatorContext context) {
		boolean correcto = false;
		int contador=0;
		if (validarDni(cliente.getDni())) {
			contador++;
		}else {
			log.error("Formato DNI incorrecto");
		}
		if (validarEmail(cliente.getEmail())) {
			contador++;
		}else {
			log.error("Formato EMAIL incorrecto");
		}
		if (validarNombreApellido(cliente.getNombre(), cliente.getApellido())) {
			contador++;
		}else {
			log.error("Formato NOMBRE APELLIDO incorrecto");
		}
		if (validarPasswd(cliente.getPasswd())) {
			contador++;
		}else {
			log.error("Formato PASSWORD incorrecto");
		}

		if (contador == 4) {
			correcto = true;
		}
//		if (validarDni(cliente.getDni()) & validarEmail(cliente.getEmail())
//				& validarNombreApellido(cliente.getNombre(), cliente.getApellido())
//				& validarPasswd(cliente.getPasswd())) {
//			correcto = true;
//		}

		return correcto;
	}

	private boolean validarDni(String dni) {
		boolean correcto = false;
		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher matcher = pattern.matcher(dni);
		if (matcher.matches()) {
			String letra = matcher.group(2);
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int index = Integer.parseInt(matcher.group(1));
			index = index % 23;
			String reference = letras.substring(index, index + 1);
			if (reference.equalsIgnoreCase(letra)) {
				correcto = true;
			} else {
				correcto = false;
			}
		} else {
			correcto = false;
		}
		return correcto;
	}

	private boolean validarNombreApellido(String nombre, String apellido) {
		String cadena = nombre + " " + apellido;
		String regex = "[A-Za-z ]*";
		return cadena.matches(regex);
	}

	private boolean validarEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	private boolean validarPasswd(String passwd) {
		boolean correcto = false;
		log.info("longitud passwd: "+passwd.length());
		if (passwd.length() > 7) {
			log.info(passwd);
			if (passwd.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")) {
				correcto = true;
			}
		}
		return correcto;
	}

}
