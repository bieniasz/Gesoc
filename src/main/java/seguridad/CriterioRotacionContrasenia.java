package seguridad;

import java.util.List;

public class CriterioRotacionContrasenia implements CriterioValidacion {


    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {

        if(AlmacenContrasenias.Instancia().contraseniaRepiteContraseniasViejas(usuario)){
            mensajesDeError.add("La contrasenia repite contrasenias viejas");
        }
    }
}
