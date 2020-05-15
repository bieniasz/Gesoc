package seguridad;

import java.util.List;

public class CriterioCaracteresEspeciales implements CriterioValidacion {


    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {
        if(!usuario.getContrasenia().matches(".*[ !#$%&'()*+,~./:<=>?@^_`{|}~].*")){
            mensajesDeError.add(("Faltan caracteres especiales"));
        }
    }
}
