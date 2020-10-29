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
        Organizacion organizacion = usuario.getRol().getOrganizacion();

        //List<OperacionIngreso> ingresos = new ArrayList<>(); //me pincha pq dice que no es static corregir
        //ingresos = OperacionIngresoDAO.getOperacionesIngreso(organizacion); //idem

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", userId);
        //parametros.put("ingresos", ingresos); corregir linea 27


        return new ModelAndView(parametros, "operacionesIngreso.hbs");
    }

}
