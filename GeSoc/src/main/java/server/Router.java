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
        OperacionesIngresoController ingresosController = new OperacionesIngresoController();

        Spark.get("/login", controllerLogin::mostrarLogin, Router.engine);
        Spark.post("/validarLogin", controllerLogin::ingresar);

        Spark.get("/operaciones", controllerOperaciones::mostrarOperaciones, Router.engine);
        Spark.get("/operacionesIngreso", ingresosController::mostrarIngresos, Router.engine);

        Spark.get("/bandejaDeMensajes", controllerBandejaDeMensajes::mostrarBandejaDeMensajes, Router.engine);
        Spark.get("/ingresos", ingresoController::mostrarIngresos, Router.engine);

        Spark.get("/egreso", controllerOperacionEgreso::nuevoEgreso, Router.engine);
        Spark.post("/egreso", controllerOperacionEgreso::guardar);
        //Spark.post("/egreso/:id", controllerOperacionEgreso::modificar, Router.engine);
    }
}
