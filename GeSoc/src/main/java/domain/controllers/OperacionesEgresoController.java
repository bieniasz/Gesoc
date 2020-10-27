package domain.controllers;

import db.DAOs.CategoriaDAO;
import db.DAOs.CategoriaDAOMemoria;
import db.DAOs.OperacionEgresoDAO;
import db.DAOs.OperacionEgresoDAOMemoria;
import domain.entities.operacionComercial.CategoriaDeOperaciones;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesEgresoController {
    private OperacionEgresoDAO operacionEgresoDAO = new OperacionEgresoDAOMemoria();
    private CategoriaDAO categoriaDAO = new CategoriaDAOMemoria();

    public ModelAndView mostrarEgresos(Request request, Response response)  throws Exception {

        List<CategoriaDeOperaciones> categorias = this.categoriaDAO.getTodasLasCategorias();

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("categorias", categorias);
        parametros.put("usuarioId", request.queryParams("usuarioId"));

        return new ModelAndView(parametros, "operacionesEgreso.hbs");
    }

}
