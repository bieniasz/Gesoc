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
		form.submit.disabled = true;
		form.submit.className = 'btn-disabled';
		form.erroresLogin.style.display = 'none';


		$.ajax({
			url: "/iniciarSesion",
			type: "Post",
			data: {
				usuario: form.usuario.value,
				contrasenia: form.contrasenia.value
			},
			dataType: 'json',
			success: function(jsonResponse){


				if (jsonResponse == "") {
					console.error('No hubo respuesta del servidor');
				}else {
					handleResponse(jsonResponse);
				}
			}
		});

		function handleResponse (responseObject) {
			if (responseObject.error == 0) {

				location.href = "/operacionesEgreso";
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