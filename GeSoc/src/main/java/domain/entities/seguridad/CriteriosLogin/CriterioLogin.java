package domain.entities.seguridad.CriteriosLogin;

import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.CriterioValidacion;
import domain.entities.usuario.Usuario;

import java.util.Arrays;
import java.util.List;

public class CriterioLogin implements CriterioValidacion {

    private AlmacenContrasenias almacen;

    public CriterioLogin() {}

    public CriterioLogin(AlmacenContrasenias almacen) {
        this.almacen = almacen;
    }

    public void validar(Usuario usuario, String contrasenia, List<String> mensajesDeError) {
        try{
            if (usuario == null || !almacen.contraseniaEsCorrecta(usuario,contrasenia)) {
                mensajesDeError.add("El usuario y/o contraseña invalido");
                almacen.crearIntentoFallidoSiAplica(usuario);
            }
        } catch (Exception e){
            System.out.println(e.getMessage() + Arrays.toString(e.getStackTrace()));
        }
    }
}
