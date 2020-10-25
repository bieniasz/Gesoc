package domain.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import db.DAOs.ContraseniasPreviasDAOMemoria;
import db.DAOs.IntentosFallidosDAOMemoria;
import db.DAOs.UserDAO;
import db.DAOs.UserDAOMemoria;
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

        //Todo el validador no está funcionando porque siempre devuelve error. Se ve que hay que manejar el almacen. Por ahora hago la prueba forzada para tener la logica.

//        ValidadorDeUsuario validador = this.getValidador();
//
//        List<String> errores = validador.validarContraseniaLogin("igna","igna");
//
//        String stringDeErrores=String.join(",",errores);
//        System.out.println(stringDeErrores);

        //Todo probar tiempos login con los DAO mySQL

        //Todo cambiar la prueba con errores oficiales y no hardcodeados.
        List<String> errores = new ArrayList<>();
        errores.add("Falla de prueba");
        errores.add("segunda falla");


        LoginRespuesta loginRespuesta = new LoginRespuesta();


        Gson gson = new Gson();



        if (errores.size() == 0){
            //response.redirect("/otroSaludo/igna");
            loginRespuesta.setError(0);
            String jsonLoginRespuesta = gson.toJson(loginRespuesta);
            return jsonLoginRespuesta;

        }
        else{
            loginRespuesta.setError(1);
            loginRespuesta.setErrores(errores);
            String jsonLoginRespuesta = gson.toJson(loginRespuesta);
            return jsonLoginRespuesta;

        }



    }

    private ValidadorDeUsuario getValidador(){
        AlmacenContrasenias almacen = new AlmacenContrasenias();
        almacen.setContraseniasPreviasDAO(new ContraseniasPreviasDAOMemoria());
        almacen.setIntentosFallidosDAO(new IntentosFallidosDAOMemoria());

        //Todo cambiar por el dao Mysql
        UserDAO usuariosDao = new UserDAOMemoria();

        ValidadorDeUsuario validador = new ValidadorDeUsuario();
        validador.setUsuarioDao(usuariosDao);
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


    public void setError(int flag){
        this.error = flag;
    }
    public void setErrores(List<String> errores){
        this.errores = errores;
    }
}
