package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OperacionIngresoController {
    public ModelAndView mostrarIngreso(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", request.queryParams("usuarioId"));
        return new ModelAndView(parametros, "operacionIngreso.hbs");
    }
}
