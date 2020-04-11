package seguridad;

import java.util.List;

public class CriterioFueraListaNegra implements CriterioValidacion {

	List<String> listaNegra;
	@Override
	public boolean validar(String contrasenia) {
		
		 
		return !listaNegra.contains(contrasenia);
	}

}
