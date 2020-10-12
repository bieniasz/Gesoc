package domain.entities.seguridad;

import domain.entities.usuario.UserDAO;
import domain.entities.seguridad.CriteriosLogin.CriterioLogin;
import domain.entities.seguridad.CriteriosLogin.CriterioTiempoLogin;
import domain.entities.usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeUsuario implements iValidadorDeUsuario{

    //TODO: armar builder para tener todos los criterios seteados
    private List<CriterioValidacion> criteriosCreacionContrasenia = new ArrayList<>();
    private AlmacenContrasenias almacenContrasenias;
    private UserDAO usuarioDao;

    public void setAlmacenContrasenias(AlmacenContrasenias almacenContrasenias) { this.almacenContrasenias = almacenContrasenias; }
    public void setUsuarioDao(UserDAO usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
    public void agregarCriterioDeCreacionDeContrasenia(CriterioValidacion criterio) {
        this.criteriosCreacionContrasenia.add(criterio);
    }


    public List<String> validarCreacionContrasenia(String usuarioId, String contrasenia) {

        Usuario user = this.getUsuario(usuarioId);
        //TODO sacar esta lista de errores y manejarlo por try catch
        final List<String> errores = new ArrayList<String>();
        this.criteriosCreacionContrasenia.forEach(criterio -> criterio.validar(user, contrasenia, errores));

        if (errores.size() == 0) {
            // TODO
           this.almacenContrasenias.registrarContrasenia(user, contrasenia);
        }

        return errores;
    }


    public List<String> validarContraseniaLogin(String usuarioId, String contrasenia) {
        Usuario usuario = this.getUsuario(usuarioId);
        List<String> mensajesDeError = new ArrayList<>();

        //TODO: esto tambien por seter, no tener los criterios ahi tan harcodeados.
        CriterioLogin criterioLogin = new CriterioLogin(this.almacenContrasenias);
        CriterioTiempoLogin criterioTiempoLogin = new CriterioTiempoLogin(this.almacenContrasenias);

        criterioLogin.validar(usuario, contrasenia, mensajesDeError);

        if(mensajesDeError.size() == 1){
            criterioTiempoLogin.errorAlLogear(usuario);
        } else{
            criterioTiempoLogin.validar(usuario,contrasenia,mensajesDeError);
        }

        return mensajesDeError;
    }

    private Usuario getUsuario(String usuarioId) {
        return this.usuarioDao.getUsuario(usuarioId);
    }
}
