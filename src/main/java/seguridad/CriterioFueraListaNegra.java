package seguridad;

import java.util.ArrayList;
import java.util.List;

public class CriterioFueraListaNegra implements CriterioValidacion {

	List<String> listaNegra;

	public CriterioFueraListaNegra(){
		this.listaNegra = new ArrayList<String>();
		listaNegra.add("123456789");
	}

	@Override
	public void validar(Usuario usuario, List<String> mensajesDeError) {
		if (listaNegra.contains(usuario.getContrasenia())) {
			mensajesDeError.add("Contrasenia pertenece a lista negra");
		}
	}
}
