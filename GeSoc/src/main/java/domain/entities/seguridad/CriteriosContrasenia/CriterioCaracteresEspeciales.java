package domain.entities.seguridad.CriteriosContrasenia;

import domain.entities.seguridad.CriterioValidacion;
import domain.entities.usuario.Usuario;

import java.util.List;

public class CriterioCaracteresEspeciales implements CriterioValidacion {


    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {
        if(!contrasenia.matches(".*[ !#$%&'()*+,~./:<=>?@^_`{|}~].*")){
            mensajesDeError.add(("Faltan caracteres especiales"));
        }
    }
}
