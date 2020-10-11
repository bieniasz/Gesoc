package seguridad.CriteriosContrasenia;

import seguridad.AlmacenContrasenias;
import seguridad.CriterioValidacion;
import usuario.Usuario;

import java.util.List;

public class CriterioRotacionContrasenia implements CriterioValidacion {

    private AlmacenContrasenias almacen;

    public CriterioRotacionContrasenia(AlmacenContrasenias almacen) {
        this.almacen = almacen;
    }
    /* TODO cuando realizamos validaciones, lo mejor es usar CustomExceptions que extiendan de Exception
     * Podrian ser PasswordRotationException que maneje un Enum ExceptionMessage que tenga la lista de errores.
     * y no una lista de errores y menos por parametro.
     */
    @Override
    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {

       if (almacen.contraseniaRepiteContraseniasViejas(usuario, contrasenia)) {
            mensajesDeError.add("La contrasenia repite contrasenias viejas");
       }
    }
}
