okpass = false;

function valida_nombre_apellidos(name) {
	var caja = name
	var expresion = /^[a-zA-ZáéíóúÁÉÍÓÚ \.]*$/
	if (expresion.test(caja.value) == true) {
		caja.setCustomValidity("")
	} else {
		if (expresion.test(caja.value) == false) {
			caja.setCustomValidity("Campo incorrecto")
		}
	}
}

function valida_mail() {
	var caja = document.getElementById("email")
	var expresion = /^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$/
	if (expresion.test(caja.value) == false) {
		caja.setCustomValidity("Email No Válido")
	} else {
		caja.setCustomValidity("")
	}
}

function nif(dni) {
	var numero
	var letr
	var letra
	var expresion_regular_dni

	expresion_regular_dni = /^\d{8}[a-zA-Z]$/;

	if (expresion_regular_dni.test(dni) == true) {
		numero = dni.substr(0, dni.length - 1);
		letr = dni.substr(dni.length - 1, 1);
		numero = numero % 23;
		letra = 'TRWAGMYFPDXBNJZSQVHLCKET';
		letra = letra.substring(numero, numero + 1);
		if (letra != letr.toUpperCase()) {
			alert('Dni erroneo, la letra del DNI no se corresponde');
		} else {
			alert('Dni correcto');
		}
	} else {
		alert('Dni erroneo, formato no válido');
	}
}

function borrar() {
	document.getElementById("info1").innerHTML = ""
	document.getElementById("info2").innerHTML = ""
}

function valida_pass() {
	var caja = document.getElementById("passwd")
	var expresion = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,32}$/
	if (expresion.test(caja.value) == false) {
		caja.setCustomValidity("Contraseña incorrecta")
	} else {
		caja.setCustomValidity("")
	}
}

function comprueba_pass() {
	pass = document.getElementById("pass").value
	pass2 = document.getElementById("pass2").value
	if (pass != pass2) {
		document.getElementById("info1").innerHTML = "Contraseña no válida"
	} else {
		okpass = true
	}
}

function validarForm() {
	return okpass
}