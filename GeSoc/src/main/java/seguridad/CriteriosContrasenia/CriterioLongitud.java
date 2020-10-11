package seguridad.CriteriosContrasenia;

import seguridad.CriterioValidacion;
import usuario.Usuario;

import java.util.List;

public class CriterioLongitud implements CriterioValidacion {

    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {

        if (contrasenia.length() <= 8) {
            mensajesDeError.add("Contrasenia muy corta, debe tener mas de 8 caracteres");
        }

    }

}
