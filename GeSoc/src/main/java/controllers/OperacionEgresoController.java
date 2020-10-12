package controllers;

import spark.Request;
import spark.Response;

public class OperacionEgresoController {

    //private OperacionEgresoDao operacionEgresoDao;

    public String saluda(Request request, Response response) {
        return "Saludos humano";
    }
}
