package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class OperacionEgresoController {

    public String saluda(Request request, Response response) {
        return "Saludos humano";
    }

    public ModelAndView mostrarEgresos(Request request, Response response) {

        return new ModelAndView(null, "pagina1.hbs");
    }
}
