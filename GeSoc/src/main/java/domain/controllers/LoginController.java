package domain.controllers;

import com.google.gson.Gson;
import db.DAOs.*;
import domain.entities.seguridad.AlmacenContrasenias;
import domain.entities.seguridad.ValidadorDeUsuario;
import domain.entities.seguridad.excepciones.LoginBloqueadoTemporalmenteException;
import domain.entities.seguridad.excepciones.UsuarioContraseniaInvalidosException;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    public ModelAndView mostrarLogin(Request request, Response response) {
        return new ModelAndView(null, "loginGesoc.hbs");
    }

    public String ingresar(Request request, Response response){
        LoginRespuesta loginRespuesta = new LoginRespuesta();
        String nombreDeUsuario = request.queryParams("usuario");
        String contrasenia     = request.queryParams("contrasenia");
        LocalDateTime horaIntento = LocalDateTime.now();


        try {
            ValidadorDeUsuario validador = this.getValidador();
            Usuario usuario = validador.validarLogin(nombreDeUsuario, contrasenia, horaIntento);
            loginRespuesta.setError(0);
            loginRespuesta.setUsuarioID(usuario.getUsuarioId());
            //Todo sumo el manejo de sessiones por arriba para ver si funciona y luego refactorizar el query.
            request.session(true);
            request.session().attribute("id", usuario.getUsuarioId());

        } catch (UsuarioContraseniaInvalidosException | LoginBloqueadoTemporalmenteException ex){
            ex.printStackTrace();
            List<String> errores = new ArrayList<>();
            errores.add(ex.getMessage());

            loginRespuesta.setError(1);
            loginRespuesta.setErrores(errores);
        }

        return new Gson().toJson(loginRespuesta);
    }

    private ValidadorDeUsuario getValidador(){
        AlmacenContrasenias almacen = new AlmacenContrasenias();
        almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMySQL());
        almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMySQL());

        ValidadorDeUsuario validador = new ValidadorDeUsuario();
        validador.setUsuarioDao(new UserDAOMySQL());
        validador.setAlmacenContrasenias(almacen);

        /* Hay que registar la contraseña en el almacen??
        almacen.registrarContrasenia(usuariosDao.getUsuario("igna"),usuariosDao.getUsuario("igna").getContrasenia());
        almacen.registrarContrasenia(usuariosDao.getUsuario("juanpa"),usuariosDao.getUsuario("igna").getContrasenia());
        */
        return validador;
    }

    public Response cerrarSesion(Request request, Response response) {
        request.session().invalidate();
        response.redirect("/");
        return response;
    }
}

class LoginRespuesta {
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
