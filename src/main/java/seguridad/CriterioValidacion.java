package seguridad;

import java.util.List;

public interface CriterioValidacion {

	void validar(String contrasenia, List<String> mensajesDeError);
}
