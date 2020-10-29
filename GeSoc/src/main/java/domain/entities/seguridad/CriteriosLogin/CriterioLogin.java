package domain.entities.seguridad.CriteriosLogin;

import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.CriterioValidacion;
import domain.entities.usuario.Usuario;

import java.util.List;

public class CriterioLogin implements CriterioValidacion {

    public CriterioLogin() {
    }

    // TODO: no poner todos los criterios en el constructor si no en metodos seter
    private AlmacenContrasenias almacen;

    public CriterioLogin(AlmacenContrasenias almancen) {
        this.almacen = almancen;
    }

    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {
        try{
        if (!almacen.compararContrasenia(usuario,contrasenia)) {
                mensajesDeError.add("El usuario y/o contraseña invalido");
                almacen.crearIntentoFallidoSiAplica(usuario);
            }
        } catch (Exception e){}


    }


}
