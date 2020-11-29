package domain.controllers;

import db.DAOs.UserDAO;
import db.DAOs.UserDAOMySQL;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class SuscripcionValidacionController {

    private UsuarioHandler usuarioHandler = new UsuarioHandler();
    private UserDAO userDAO = new UserDAOMySQL();

    public ModelAndView mostrarSuscripciones(Request request, Response response) {

        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = this.userDAO.buscarUsuarioPoruserId(usuarioIDSpark);

        Map<String, Object> parametros = new HashMap<>();
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        return new ModelAndView(parametros, "suscripciones.hbs");
    }
}
