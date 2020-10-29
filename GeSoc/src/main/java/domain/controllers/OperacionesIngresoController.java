package domain.controllers;

import domain.entities.ProveedorDocComer.Proveedor;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperacionesIngresoController {

    public ModelAndView mostrarIngresos(Request request, Response response)  throws Exception{

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", request.queryParams("usuarioId"));

        return new ModelAndView(parametros,"operacionesIngreso.hbs");
    }

}
