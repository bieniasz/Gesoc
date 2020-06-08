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

    public List<String> validarCreacionContrasenia(String usuario, String contrasenia) {

        final List<String> errores = new ArrayList<String>();
        this.criteriosCreacionContrasenia.forEach(criterio -> criterio.validar(usuario,contrasenia,errores));

        if (errores.size() == 0) {
            AlmacenContrasenias.Instancia().registrarContrasenia(usuario, contrasenia);
        }

        return errores;
    }

	/**
	 * 1- los metodos en java comienzan con minuscula.
	 * 2- si un metodo no se utiliza en el codigo no debe existir.
	 * @param usuario
	 * @return
	 */
	public List<String> validarContraseniaLogin(String usuario, String contrasenia) {
        List<String> mensajesDeError = new ArrayList<String>();
        this.criteriosLogin.stream().forEach(criterio -> criterio.validar(usuario, contrasenia , mensajesDeError));

        return mensajesDeError;
    }

}
