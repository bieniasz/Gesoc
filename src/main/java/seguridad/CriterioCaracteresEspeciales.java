package seguridad;

import java.util.ArrayList;
import java.util.List;

public class CriterioCaracteresEspeciales implements CriterioValidacion {


    @Override
    public void validar(String contrasenia, List<String> mensajesDeError) {

        if(!contrasenia.matches(".*[ !#$%&'()*+,~./:<=>?@^_`{|}~].*")){
            mensajesDeError.add(("Faltan caracteres especiales"));
        }
    }
}
