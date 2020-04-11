package seguridad;

import java.util.List;

public class ValidadorContrasenia {

	List<CriterioValidacion> criteriosLogin;
	List<CriterioValidacion> criteriosCreacion;
	
	boolean validarContrase�aCreacion(String contrasenia) {
		
		
		return	this.criteriosCreacion.stream().allMatch(criterio->criterio.validar(contrasenia)==true);
		
	}

}
