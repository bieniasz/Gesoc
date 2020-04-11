package seguridad;

import java.util.List;

public class ValidadorContrase�a {

	List<CriterioValidacion> criteriosLogin;
	List<CriterioValidacion> criteriosCreacion;
	
	boolean validarContrase�aCreacion(String contrase�a) {
		
		
		return	this.criteriosCreacion.stream().allMatch(criterio->criterio.validar(contrase�a)==true);
		
	}

}
