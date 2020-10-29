package domain.controllers;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class BandejaDeMensajesController {

    public ModelAndView mostrarBandejaDeMensajes(Request request, Response response) {

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("usuarioId", request.queryParams("usuarioId"));

        return new ModelAndView(parametros, "bandejaDeMensajes.hbs");
    }
}

