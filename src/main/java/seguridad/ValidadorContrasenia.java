package seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorContrasenia {

	private static List<CriterioValidacion> criteriosLogin;
	private static List<CriterioValidacion> criteriosCreacionContrasenia;

	public ValidadorContrasenia()
	{
		this.criteriosLogin = new ArrayList<CriterioValidacion>();

		this.criteriosCreacionContrasenia = new ArrayList<CriterioValidacion>();
		this.criteriosCreacionContrasenia.add(new CriterioCaracteresEspeciales());
		this.criteriosCreacionContrasenia.add(new CriterioFueraListaNegra());
		this.criteriosCreacionContrasenia.add(new CriterioLongitud());
		this.criteriosCreacionContrasenia.add(new CriterioMinusculasYMayusculas());
	}

	public List<String> ValidarCreacionContrasenia(Usuario usuario) {

		List<String> mensajesDeError = new ArrayList<String>();
		this.criteriosCreacionContrasenia.stream().forEach( criterio -> criterio.validar(usuario, mensajesDeError) );

		return mensajesDeError;
	}

	public List<String> ValidarContraseniaLogin(Usuario usuario) {
		List<String> mensajesDeError = new ArrayList<String>();
		this.criteriosLogin.stream().forEach( criterio -> criterio.validar(usuario, mensajesDeError) );

		return mensajesDeError;
	}

}
