package seguridad;

import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorContrasenia {

    private final List<CriterioValidacion> criteriosLogin;
    private final List<CriterioValidacion> criteriosCreacionContrasenia;

    public ValidadorContrasenia() {
        this.criteriosLogin = new ArrayList<CriterioValidacion>();

        this.criteriosCreacionContrasenia = new ArrayList<CriterioValidacion>();
        this.criteriosCreacionContrasenia.add(new CriterioCaracteresEspeciales());
        this.criteriosCreacionContrasenia.add(new CriterioFueraListaNegra());
        this.criteriosCreacionContrasenia.add(new CriterioLongitud());
        this.criteriosCreacionContrasenia.add(new CriterioMinusculasYMayusculas());
        this.criteriosCreacionContrasenia.add(new CriterioRotacionContrasenia());
    }

    public List<String> validarCreacionContrasenia(Usuario usuario) {

        final List<String> errores = new ArrayList<String>();
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
