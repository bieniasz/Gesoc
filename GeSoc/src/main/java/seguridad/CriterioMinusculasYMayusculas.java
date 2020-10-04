package seguridad;

import java.util.List;

public class CriterioMinusculasYMayusculas implements CriterioValidacion {

    @Override
    public void validar(String usuario, String contrasenia, List<String> mensajesDeError) {

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
