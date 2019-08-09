function checkEmail() {
	var email = document.getElementById('input_email');
	var filter = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	if (!filter.test(email.value)) {
		document.getElementById('no').innerHTML = "El email introducido no es correcto";
		return false;
	} else {
		document.getElementById('si').innerHTML = "";
		return true;
	}
}