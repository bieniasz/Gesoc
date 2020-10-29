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

    public ModelAndView mostrarIngresos(Request request, Response response)  throws Exception {

        String userId = request.queryParams("usuarioId");
        Usuario usuario = userDAO.buscarUsuarioPoruserId(userId);
        int organizacionId = usuario.getRol().getOrganizacion().getId();
        String nombreFicticio = usuario.getRol().getOrganizacion().getNombreFicticio();

        List<OperacionIngreso> ingresos = new ArrayList<>();
        ingresos = operacionIngresoDAO.getOperacionesIngresoPorOrganizacion(organizacionId);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", userId);
        parametros.put("ingresos", ingresos);
        parametros.put("nombreFicticio", nombreFicticio);


        return new ModelAndView(parametros, "operacionesIngreso.hbs");
    }

}
