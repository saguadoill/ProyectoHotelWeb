$(document).ready(function() {
	
	okpass = false;

	function valida_nombre(name) {
		
		var caja = document.getElementById("nombre");
		var expresion = /^[a-zA-ZáéíóúÁÉÍÓÚ \.]*$/
		if (expresion.test(name) == true) {
			caja.setCustomValidity("")
		} else {
			caja.setCustomValidity("Formato incorrecto")
		}
			
	}
	
	function valida_apellido(apellidos) {
		var caja = document.getElementById("apellido");
		var expresion = /^[a-zA-ZáéíóúÁÉÍÓÚ \.]*$/
		if (expresion.test(apellidos) == true) {
			caja.setCustomValidity("")
		} else {
			caja.setCustomValidity("Formato incorrecto")
		}
			
	}

	function valida_mail(email) {
		var caja = document.getElementById("email")
		var expresion = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$/
		if (expresion.test(email) == false) {
			caja.setCustomValidity("Email no válido")
		} else {
			caja.setCustomValidity("")
		}
	}

	function nif(dni) {
		var numero
		var letr
		var letra
		var expresion_regular_dni
		var caja = document.getElementById("dni");

		expresion_regular_dni = /^\d{8}[a-zA-Z]$/;

		if (expresion_regular_dni.test(dni) == true) {
			numero = dni.substr(0, dni.length - 1);
			letr = dni.substr(dni.length - 1, 1);
			numero = numero % 23;
			letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
			letra = letra.substring(numero, numero + 1);
			if (letra != letr.toUpperCase()) {
				caja.setCustomValidity("La letra no corresponde a este DNI")
			} else {
				caja.setCustomValidity("")
			}
		} else {
			caja.setCustomValidity("Formato de DNI no válido")
		}
	}

	function borrar() {
		document.getElementById("info1").innerHTML = ""
		document.getElementById("info2").innerHTML = ""
	}

	function valida_pass(pass) {
		var caja = document.getElementById("passwd")
		var expresion = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{4,32}$/
		if (expresion.test(pass) == false) {
			caja.setCustomValidity("La contraseña debe tener al menos una mayúscula y un número y un tamaño minimo de 4 caracteres")
		} else {
			caja.setCustomValidity("")
		}
	}

	function comprueba_pass() {
		pass = document.getElementById("passwd").value
		pass2 = document.getElementById("pass2").value
		if (pass != pass2) {
			document.getElementById("pass2").setCustomValidity("Las contraseñas no coinciden")
		} else {
			document.getElementById("pass2").setCustomValidity("")
		}
	}

	function validarForm() {
		return okpass
	}
	
	
	// ---- Cambios Ricardo START ---- // 
	
	
	$('#nombre').on('blur', function() {
		var nombre = $(this).val();
		valida_nombre(nombre);
	})
	
	$('#apellido').on('blur', function() {
		var apellido = $(this).val();
		valida_apellido(apellido)
	})
		
	$('#email').on('blur', function() {
		var email = $(this).val();
		valida_mail(email);
	})
	
	$('#dni').on('blur', function() {
		var dni = $(this).val();
		nif(dni)
	})
	
	$('#passwd').on('blur', function() {
		var pass = $(this).val();
		valida_pass(pass)
	})
	
	$('#pass2').on('blur', function() {
		comprueba_pass();
	})
	
	
})
