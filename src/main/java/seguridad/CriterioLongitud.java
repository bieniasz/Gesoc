package seguridad;

import java.util.List;

public class CriterioLongitud implements CriterioValidacion {

	@Override
	public void validar(Usuario usuario, List<String> mensajesDeError) {

		if(usuario.getContrasenia().length() <= 8)
		{
			mensajesDeError.add("Contrasenia muy corta, debe tener mas de 8 caracteres");
		}

	}

}
