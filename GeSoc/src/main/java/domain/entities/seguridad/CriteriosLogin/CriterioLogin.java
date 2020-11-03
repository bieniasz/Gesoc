package domain.entities.seguridad.CriteriosLogin;

import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.CriterioValidacion;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import domain.entities.usuario.Usuario;

import java.util.List;

public class CriterioLogin implements CriterioValidacion {

    private AlmacenContrasenias almacen;

    public CriterioLogin() {}

    public CriterioLogin(AlmacenContrasenias almacen) {
        this.almacen = almacen;
    }

    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) throws UsuarioContraseniaInvalidosException {
        //El usuario acá ya no es null
        //TODO implementar cifrado/hash para las contraseñas
        if(!usuario.getContrasenia().equals(contrasenia)) {
            this.almacen.registrarIntentoFallido(usuario);
            throw new UsuarioContraseniaInvalidosException();
        }
    }
}
