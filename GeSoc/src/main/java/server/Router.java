package server;

import domain.controllers.OperacionEgresoController;
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
        OperacionEgresoController controller = new OperacionEgresoController();

        Spark.get("/saludo", (request, response) -> "hola " + request.queryParams("nombre"));
        Spark.get("/otroSaludo/:nombre", (request, response) -> "hola " + request.params("nombre"));
        Spark.get("/saludoMaquina", controller::saluda);
        Spark.get("/mostrarEgresos", controller::mostrarEgresos);

    }
}
