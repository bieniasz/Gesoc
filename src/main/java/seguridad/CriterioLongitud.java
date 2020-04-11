package seguridad;

public class CriterioLongitud implements CriterioValidacion {

	@Override
	public boolean validar(String contrasenia) {
		
		return contrasenia.length()>=8;
	}

}
