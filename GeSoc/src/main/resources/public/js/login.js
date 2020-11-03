$(document).ready(function(){
	const inputs = document.querySelectorAll(".input");
	
	inputs.forEach(input => {
		input.addEventListener("focus", addcl);
		input.addEventListener("blur", remcl);
	});

	loadValidationBtn();
});

function addcl(){
	let parent = this.parentNode.parentNode;
	parent.classList.add("focus");
}

function remcl(){
	let parent = this.parentNode.parentNode;
	if(this.value == ""){
		parent.classList.remove("focus");
	}
}

function loadValidationBtn(){
	const form = {
		usuario: document.getElementById('usuario'),
		contrasenia: document.getElementById('contrasenia'),
		submit: document.getElementById('btn-submit'),
		erroresLogin: document.getElementById('erroresLogin')
	};

	form.submit.addEventListener('click', () => {
	    var horaIntento = new Date().getTime();
		form.submit.disabled = true;
		form.submit.className = 'btn-disabled';
		form.erroresLogin.style.display = 'none';
		console.log(form.usuario.value, form.contrasenia.value);

		$.ajax({
			url: "/validarLogin",
			type: "Post",
			data: {
				usuario: form.usuario.value,
				contrasenia: form.contrasenia.value,
				horaIntento: horaIntento
			},
			dataType: 'json',
			success: function(jsonResponse){
				console.log(jsonResponse);
				console.log(jsonResponse.error);
				console.log(jsonResponse.errores);

				if (jsonResponse == "") {
					console.error('No hubo respuesta del servidor');
				}else {
					handleResponse(jsonResponse);
				}
			}
		});

		function handleResponse (responseObject) {
			if (responseObject.error == 0) {
				var id = responseObject.usuarioID;
				location.href = "/operacionesEgreso?usuarioId=" + id;
			} else {
				while (form.erroresLogin.firstChild) {
					form.erroresLogin.removeChild(form.erroresLogin.firstChild);
				}

				responseObject.errores.forEach((error) => {
					const li = document.createElement('li');
					li.textContent = error;
					form.erroresLogin.appendChild(li);
				});

				form.erroresLogin.style.display = "block";
			}

			form.submit.disabled = false;
			form.submit.className = 'btn';
		}
	});
}