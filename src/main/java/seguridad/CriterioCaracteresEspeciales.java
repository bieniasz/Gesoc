package seguridad;
//TODO si un import no se utiliza no lo agreguemos.
import java.util.ArrayList;
import java.util.List;

public class CriterioCaracteresEspeciales implements CriterioValidacion {


    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {

        if(!usuario.getContrasenia().matches(".*[ !#$%&'()*+,~./:<=>?@^_`{|}~].*")){
            mensajesDeError.add(("Faltan caracteres especiales"));
        }
    }
}
