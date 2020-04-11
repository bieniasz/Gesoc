package seguridad;

import java.util.List;

public class ValidadorContraseña {

	List<CriterioValidacion> criteriosLogin;
	List<CriterioValidacion> criteriosCreacion;
	
	boolean validarContraseñaCreacion(String contraseña) {
		
		
		return	this.criteriosCreacion.stream().allMatch(criterio->criterio.validar(contraseña)==true);
		
	}

}
