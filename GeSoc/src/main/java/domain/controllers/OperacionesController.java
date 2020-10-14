package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OperacionesController {

    public ModelAndView mostrarOperaciones(Request request, Response response) {

        return new ModelAndView(null, "operaciones.hbs");
    }
}
