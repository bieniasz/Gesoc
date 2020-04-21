package seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ValidadorContrasenia {

	private static List<CriterioValidacion> criteriosLogin;
	private static List<CriterioValidacion> criteriosCreacion;

	public ValidadorContrasenia()
	{
		this.criteriosLogin = new ArrayList<CriterioValidacion>();
		this.criteriosLogin.add(new CriterioLongitud());
		this.criteriosLogin.add(new CriterioFueraListaNegra());

		this.criteriosCreacion = new ArrayList<CriterioValidacion>();
		this.criteriosCreacion.add(new CriterioMinusculasYMayusculas());
	}

	public List<String> ValidarContraseniaCreacion(String constrasenia) {

		List<String> mensajesDeError = new ArrayList<String>();
		this.criteriosCreacion.stream().forEach( criterio -> criterio.validar(constrasenia, mensajesDeError) );

		return mensajesDeError;
	}

	public List<String> ValidarContraseniaLogin(String constrasenia) {
		List<String> mensajesDeError = new ArrayList<String>();
		this.criteriosLogin.stream().forEach( criterio -> criterio.validar(constrasenia, mensajesDeError) );

		return mensajesDeError;
	}

}
