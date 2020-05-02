package seguridad;

import java.util.ArrayList;
import java.util.List;

public class ValidadorContrasenia {

	//TODO no entiendo porque hacen static estos atributos y luego los acceden con this.
	//El uso de static es para metodos static.
    private static List<CriterioValidacion> criteriosLogin;
    private static List<CriterioValidacion> criteriosCreacionContrasenia;

    public ValidadorContrasenia() {
        this.criteriosLogin = new ArrayList<CriterioValidacion>();

        this.criteriosCreacionContrasenia = new ArrayList<CriterioValidacion>();
        this.criteriosCreacionContrasenia.add(new CriterioCaracteresEspeciales());
        this.criteriosCreacionContrasenia.add(new CriterioFueraListaNegra());
        this.criteriosCreacionContrasenia.add(new CriterioLongitud());
        this.criteriosCreacionContrasenia.add(new CriterioMinusculasYMayusculas());
        this.criteriosCreacionContrasenia.add(new CriterioRotacionContrasenia());
    }

    //TODO los nombres de metodos en java comienzan con minuscula.
    public List<String> ValidarCreacionContrasenia(Usuario usuario) {

        final List<String> errores = new ArrayList<String>();
		/**
		 * TODO el largo del nombre de una variable esta relacionado con el scope de donde esta definida.
		 * Por ejemplo criterio, usando en su lugar c es valido y no necesita ser tan descriptiva y
		 * se entiende que esta haciendose en esa linea.
		 * this.criteriosCreacionContrasenia.forEach(c -> c.validar(usuario, errores));
		 */
        this.criteriosCreacionContrasenia.forEach(criterio -> criterio.validar(usuario, errores));

        if (errores.size() == 0) {
            AlmacenContrasenias.Instancia().registrarContrasenia(usuario);
        }

        return errores;
    }

	/**
	 * 1- los metodos en java comienzan con minuscula.
	 * 2- si un metodo no se utiliza en el codigo no debe existir.
	 * @param usuario
	 * @return
	 */
	public List<String> ValidarContraseniaLogin(Usuario usuario) {
        List<String> mensajesDeError = new ArrayList<String>();
        this.criteriosLogin.stream().forEach(criterio -> criterio.validar(usuario, mensajesDeError));

        return mensajesDeError;
    }

}
