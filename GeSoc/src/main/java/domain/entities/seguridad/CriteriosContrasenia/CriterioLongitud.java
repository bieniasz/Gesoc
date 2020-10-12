package domain.entities.seguridad.CriteriosContrasenia;

import domain.entities.seguridad.CriterioValidacion;
import domain.entities.usuario.Usuario;

import java.util.List;

public class CriterioLongitud implements CriterioValidacion {

    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {

        if (contrasenia.length() <= 8) {
            mensajesDeError.add("Contrasenia muy corta, debe tener mas de 8 caracteres");
        }

    }

}
