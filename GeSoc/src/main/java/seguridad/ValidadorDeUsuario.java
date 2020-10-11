package seguridad;

import db.UserDAO;
import seguridad.CriteriosLogin.CriterioLogin;
import seguridad.CriteriosLogin.CriterioTiempoLogin;
import usuario.Usuario;

import java.util.ArrayList;
import java.util.List;

public class ValidadorDeUsuario implements iValidadorDeUsuario{

    //TODO: cuidado con los DAO y el almacen, todo debe ir con su set, nada por contructor.
    private List<CriterioValidacion> criteriosCreacionContrasenia = new ArrayList<>();
    private AlmacenContrasenias almacenContrasenias = new AlmacenContrasenias();
    private UserDAO usuarioDao;

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


    public Usuario crearUsuario(String usuario, String contrasenia) throws Exception{
        List<String> mensajesDeError = this.validarCreacionContrasenia(usuario,contrasenia);

        try {
            if (mensajesDeError.size() > 0){
                throw new Exception();
            } else {
                // TODO return new Usuario(usuario);
            }
        } catch (Exception e) {
            //TODO cambiar para que no haga print por pantalla.
            System.out.println("El usuario " + "\"" + usuario + "\"" + " no pudo ser creado porque se presentaron los siguientes errores:");
            System.out.println(mensajesDeError);
            throw e;
        }

        Usuario user = new Usuario();
        user.setUsuarioId(usuario);
        user.setContrasenia(contrasenia);
        return user;
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
