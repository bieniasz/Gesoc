package domain.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import db.DAOs.*;
import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.ValidadorDeUsuario;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import javax.security.sasl.RealmCallback;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public ModelAndView mostrarLogin(Request request, Response response) {
        return new ModelAndView(null, "loginGesoc.hbs");
    }

    public String ingresar(Request request, Response response){
        String nombreDeUsuario = request.queryParams("usuario");
        String contrasenia     = request.queryParams("contrasenia");
        System.out.println(nombreDeUsuario);
        System.out.println(contrasenia);

        ValidadorDeUsuario validador = this.getValidador();
        List<String> errores = validador.validarContraseniaLogin(nombreDeUsuario,contrasenia);
        System.out.println(errores);

        //Todo probar tiempos login con los DAO mySQL
        //Todo el id del userDao debería volver del validador si no tiene errores.
        UserDAO usuarioDao = new UserDAOMySQL();

        LoginRespuesta loginRespuesta = new LoginRespuesta();
        Gson gson = new Gson();

        if (errores.size() == 0){
            loginRespuesta.setError(0);
            Usuario usuario = usuarioDao.buscarUsuarioPoruserId(nombreDeUsuario);
            loginRespuesta.setUsuarioID(usuario.getUsuarioId());
        }
        else{
            loginRespuesta.setError(1);
            loginRespuesta.setErrores(errores);
        }

        return gson.toJson(loginRespuesta);
    }

    private ValidadorDeUsuario getValidador(){
        AlmacenContrasenias almacen = new AlmacenContrasenias();

        almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMySQL());
        almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMySQL());
        UserDAO usuarioDao = new UserDAOMySQL();

        ValidadorDeUsuario validador = new ValidadorDeUsuario();
        validador.setUsuarioDao(usuarioDao);
        validador.setAlmacenContrasenias(almacen);

        /* Hay que registar la contraseña en el almacen??
        almacen.registrarContrasenia(usuariosDao.getUsuario("igna"),usuariosDao.getUsuario("igna").getContrasenia());
        almacen.registrarContrasenia(usuariosDao.getUsuario("juanpa"),usuariosDao.getUsuario("igna").getContrasenia());
        */
        return validador;
    }

}

class LoginRespuesta{
    int error;
    List<String> errores = new ArrayList<>();
    String usuarioID;

    public void setError(int flag){
        this.error = flag;
    }
    public void setErrores(List<String> errores){
        this.errores = errores;
    }
    public void setUsuarioID(String id){
        this.usuarioID = id;
    }
}
