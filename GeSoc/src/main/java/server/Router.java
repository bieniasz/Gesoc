package server;

import domain.controllers.*;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {

    private static HandlebarsTemplateEngine engine;

    private static void initEngine() {
        Router.engine = HandlebarsTemplateEngineBuilder
                .create()
                .withDefaultHelpers()
                .withHelper("isTrue", BooleanHelper.isTrue)
                .build();
    }

    public static void init() {
        Router.initEngine();
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        LoginController controllerLogin = new LoginController();
        OperacionesController controllerOperaciones = new OperacionesController();
        BandejaDeMensajesController controllerBandejaDeMensajes = new BandejaDeMensajesController();
        OperacionEgresoController controllerOperacionEgreso = new OperacionEgresoController();
        OperacionIngresoController ingresoController = new OperacionIngresoController();

        //TODO: borrar acciones de ejemplo
        Spark.get("/saludo", (request, response) -> "hola " + request.queryParams("nombre"));
        Spark.get("/otroSaludo/:nombre", (request, response) -> "hola " + request.params("nombre"));
        Spark.get("/saludoMaquina", controllerOperacionEgreso::saluda);


        Spark.get("/login", controllerLogin::mostrarLogin, Router.engine);
        Spark.get("/operaciones", controllerOperaciones::mostrarOperaciones, Router.engine);
        Spark.get("/bandejaDeMensajes", controllerBandejaDeMensajes::mostrarBandejaDeMensajes, Router.engine);
        Spark.get("/mostrarEgresos", controllerOperacionEgreso::mostrarEgresos, Router.engine);
        Spark.get("/ingresos", ingresoController::mostrarIngresos, Router.engine);
        Spark.post("/egreso", controllerOperacionEgreso::guardar, Router.engine);
    }
}
