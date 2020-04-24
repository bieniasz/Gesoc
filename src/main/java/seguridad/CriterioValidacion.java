package seguridad;

import java.util.List;

public interface CriterioValidacion {

	void validar(Usuario usuario, List<String> mensajesDeError);
}
