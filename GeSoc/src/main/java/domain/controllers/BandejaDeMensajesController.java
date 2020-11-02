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

    public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) {

        String usuarioID = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioID);
        String nombreFicticioOrganizacion = usuario.getRol().getOrganizacion().getNombreFicticio();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", usuarioID);
        parametros.put("nombreFicticioOrganizacion", nombreFicticioOrganizacion);

        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }
}

