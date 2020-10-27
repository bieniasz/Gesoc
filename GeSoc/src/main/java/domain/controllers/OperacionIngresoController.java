package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OperacionIngresoController {
    public ModelAndView mostrarIngreso(Request request, Response response) {
        return new ModelAndView(null, "operacionIngreso.hbs");
    }
}
