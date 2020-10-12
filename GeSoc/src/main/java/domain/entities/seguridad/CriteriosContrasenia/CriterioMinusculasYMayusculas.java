package domain.entities.seguridad.CriteriosContrasenia;

import domain.entities.seguridad.CriterioValidacion;
import domain.entities.usuario.Usuario;

import java.util.List;

public class CriterioMinusculasYMayusculas implements CriterioValidacion {

    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {

        boolean tieneMayusculas = contrasenia.matches(".*[ABCDEFGHIJKLMNOPQRSTUVWXYZ].*");
        if (!tieneMayusculas) {
            mensajesDeError.add(("Faltan letras mayusculas a la contrasenia"));
        }

        boolean tieneMinusculas = contrasenia.matches(".*[abcdefghijklmnopqrstuvwxyz].*");
        if (!tieneMinusculas) {
            mensajesDeError.add(("Faltan letras minusculas a la contrasenia"));
        }
    }
}
