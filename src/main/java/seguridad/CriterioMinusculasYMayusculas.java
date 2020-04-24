package seguridad;

import java.util.List;

public class CriterioMinusculasYMayusculas implements CriterioValidacion {

    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {

        boolean tieneMayusculas = usuario.getContrasenia().matches(".*[ABCDEFGHIJKLMNOPQRSTUVWXYZ].*");
        if(!tieneMayusculas){
            mensajesDeError.add(("Faltan letras mayusculas a la contrasenia"));
        }

        boolean tieneMinusculas = usuario.getContrasenia().matches(".*[abcdefghijklmnopqrstuvwxyz].*");
        if(!tieneMinusculas){
            mensajesDeError.add(("Faltan letras minusculas a la contrasenia"));
        }
    }
}
