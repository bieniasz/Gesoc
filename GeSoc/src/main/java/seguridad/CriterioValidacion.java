package seguridad;

import usuario.Usuario;

import java.util.List;

public interface CriterioValidacion {

	public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError);
}
