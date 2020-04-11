package seguridad;

public class CriterioOchoCaracteres implements CriterioValidacion {

	@Override
	public boolean validar(String contrasenia) {
		
		return contrasenia.length()>=8;
	}

}
