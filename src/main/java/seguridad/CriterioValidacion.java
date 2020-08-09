package seguridad;

import java.util.List;

public interface CriterioValidacion {

	public void validar(String usuario, String contrasenia, List<String> mensajesDeError);
}
