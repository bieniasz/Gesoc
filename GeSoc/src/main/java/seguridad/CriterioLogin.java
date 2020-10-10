package seguridad;

import usuario.Usuario;

import java.util.List;

public class CriterioLogin implements CriterioValidacion{

    // TODO: no poner todos los criterios en el constructor si no en metodos seter
    private AlmacenContrasenias almacen;

    public CriterioLogin(AlmacenContrasenias almancen) {
        this.almacen = almancen;
    }

    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {
        try{
        if (false == almacen.compararContrasenia(usuario,contrasenia)) {
                mensajesDeError.add("El usuario y/o contraseña invalido");
            }
        } catch (Exception e){}

        almacen.crearIntentoFallidoSiAplica(usuario);
    }



}
