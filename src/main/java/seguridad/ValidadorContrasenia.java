package seguridad;

import java.util.List;

public class ValidadorContrasenia {

	private List<CriterioValidacion> criteriosLogin;
	private List<CriterioValidacion> criteriosCreacion;
	
	boolean validarContraseniaCreacion(String contrasenia) {
		
		
		return	this.criteriosCreacion.stream().allMatch(criterio->criterio.validar(contrasenia)==true);
		
	}

}
