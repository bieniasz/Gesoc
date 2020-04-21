package seguridad;

import java.util.List;

public class CriterioMinusculasYMayusculas implements CriterioValidacion {

    @Override
    public void validar(String contrasenia, List<String> mensajesDeError) {
        if(sonTodasMayuscula(contrasenia)){
            mensajesDeError.add(("Faltan letras minusculas a la contrasenia"));
        }
        if(sonTodasMinuscula(contrasenia)){
            mensajesDeError.add(("Faltan letras mayusculas a la contrasenia"));
        }
    }

    public boolean sonTodasMayuscula(String contrasenia) {
        return contrasenia.equals(contrasenia.toUpperCase());
    }

    public boolean sonTodasMinuscula(String contrasenia) {
        return contrasenia.equals(contrasenia.toLowerCase());
    }
}
