package domain.controllers;

import db.DAOs.*;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.OperacionIngreso;
import domain.entities.organizacion.Organizacion;
import domain.entities.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesIngresoController {
    private OperacionIngresoDAOMySQL operacionIngresoDAO = new OperacionIngresoDAOMySQL();
    private UserDAO userDAO = new UserDAOMySQL();
    private UsuarioHandler usuarioHandler = new UsuarioHandler();

    public ModelAndView mostrarIngresos(Request request, Response response)  throws Exception {

        //String usuarioID = request.queryParams("usuarioId");
        String usuarioIDSpark = request.session().attribute("id");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(usuarioIDSpark);
        int organizacionId = usuario.getRol().getOrganizacion().getId();

        List<OperacionIngreso> ingresos = new ArrayList<>();
        ingresos = operacionIngresoDAO.getOperacionesIngresoPorOrganizacion(organizacionId);

        Map<String, Object> parametros = new HashMap<>();

        //Devuelve la lista de parametros con la información del rol de usuario y los datos que van en el menú.
        usuarioHandler.agregarDatosDeUsuario(parametros,usuario);

        parametros.put("ingresos", ingresos);

        return new ModelAndView(parametros, "operacionesIngreso.hbs");
    }

}
