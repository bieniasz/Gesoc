package domain.controllers;

import db.DAOs.UserDAO;
import db.DAOs.UserDAOMySQL;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BandejaDeMensajesController {

    private UserDAO userDAO = new UserDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();

    public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) {

        //String usuarioID = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }
}

