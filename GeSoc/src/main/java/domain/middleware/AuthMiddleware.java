package domain.middleware;

import db.DAOs.UserDAO;
import db.DAOs.UserDAOMySQL;
import domain.entities.usuario.Usuario;
import spark.Request;
import spark.Response;

public class AuthMiddleware {
    private UserDAO userDAO = new UserDAOMySQL();

    public Response verificarSesionLogin(Request request, Response response){
        if(!request.session().isNew() && request.session().attribute("id") != null){


            response.redirect("/operacionesEgreso");
        }
        return response;
    }
    public Response verificarSesionGeneral(Request request, Response response){
        if(request.session().isNew() || request.session().attribute("id") == null){

            response.redirect("/");

         }
        return response;
    }

}