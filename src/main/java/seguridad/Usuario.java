package seguridad;

import java.util.List;

public class Usuario {

	String nombre;
	String contrasenia;
	String tipo;
	
	void iniciarSesion() {
		ValidadorContrasenia validador = new ValidadorContrasenia();
		List<String> loginErrors = validador.ValidarContraseniaLogin(this.contrasenia);
		//if(loginErrors.size() > 0) {
		//	throw new Exception("Error al loguear");
		//}
	}
}
