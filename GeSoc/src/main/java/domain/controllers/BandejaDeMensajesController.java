package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class BandejaDeMensajesController {

    public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) {

        return new ModelAndView(null, "bandejaDeMensajes.hbs");
    }
}

