package seguridad;

import java.util.List;

public class CriterioRotacionContrasenia implements CriterioValidacion {




    /**
     * TODO cuando realizamos validaciones, lo mejor es usar CustomExceptions que extiendan de Exception
     * Podrian ser PasswordRotationException que maneje un Enum ExceptionMessage que tenga la lista de errores.
     * y no una lista de errores y menos por parametro.
     * @param usuario
     * @param mensajesDeError
     */
    @Override
    public void validar(Usuario usuario, List<String> mensajesDeError) {

        if (AlmacenContrasenias.Instancia().contraseniaRepiteContraseniasViejas(usuario)) {
            mensajesDeError.add("La contrasenia repite contrasenias viejas");
        }
    }
}
