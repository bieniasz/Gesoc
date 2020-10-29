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
        //OperacionesController controllerOperaciones = new OperacionesController(); OLD VERSION
        OperacionesEgresoController egresosController = new OperacionesEgresoController();
        BandejaDeMensajesController controllerBandejaDeMensajes = new BandejaDeMensajesController();
        OperacionEgresoController controllerOperacionEgreso = new OperacionEgresoController();
        OperacionIngresoController controllerOperacionIngreso = new OperacionIngresoController();
        PresupustoController controllerPresupuesto = new PresupustoController();

        Spark.get("/login", controllerLogin::mostrarLogin, Router.engine);
        Spark.post("/validarLogin", controllerLogin::ingresar);

        //Spark.get("/operaciones", controllerOperaciones::mostrarOperaciones, Router.engine); OLD VERSION
        Spark.get("/operacionesEgreso", egresosController::mostrarEgresos, Router.engine);

        Spark.get("/bandejaDeMensajes", controllerBandejaDeMensajes::mostrarBandejaDeMensajes, Router.engine);

        Spark.get("/egreso", controllerOperacionEgreso::nuevoEgreso, Router.engine);
        Spark.post("/egreso", controllerOperacionEgreso::guardar);
        Spark.get("/egresoEditar", controllerOperacionEgreso::editarEgreso, Router.engine);
        Spark.post("/egresoEditar", controllerOperacionEgreso::guardarEditarEgreso);
        
        Spark.get("/ingreso", controllerOperacionIngreso::nuevoIngreso, Router.engine);
        Spark.post("/ingreso", controllerOperacionIngreso::guardar);
        Spark.get("/ingresoEditar", controllerOperacionIngreso::editarIngreso, Router.engine);
        Spark.post("/ingresoEditar", controllerOperacionIngreso::guardarEditarIngreso);

     
        
        Spark.get("/presupuesto", controllerPresupuesto::nuevoPresupuesto, Router.engine);
        //Spark.post("/presupuesto", controllerPresupuesto::guardar, Router.engine);

        
        
        
        
        
        
        
        
   

      
    }
}
