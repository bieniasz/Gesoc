package seguridad;

import java.util.List;

public interface CriterioValidacion {

	void validar(String usuario, String contrasenia, List<String> mensajesDeError);
}
