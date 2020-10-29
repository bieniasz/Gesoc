package domain.controllers;

import db.DAOs.*;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import domain.entities.operacionComercial.OperacionEgreso;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesEgresoController {
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMySQL();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMemoria();

    public ModelAndView mostrarEgresos(Request request, Response response)  throws Exception {

        String usuarioID = request.queryParams("usuarioId");

        List<OperacionEgreso> egresos = new OperacionEgresoDAOMemoria().getOperacionesEgreso();

        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", usuarioID);
        parametros.put("egresos", egresos);

        return new ModelAndView(parametros, "operacionesEgreso.hbs");
    }

}
