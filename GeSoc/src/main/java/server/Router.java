package server;

import domain.controllers.*;
import domain.middleware.AuthMiddleware;
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

        OperacionesEgresoController egresosController = new OperacionesEgresoController();
        OperacionesIngresoController ingresosController = new OperacionesIngresoController();
        BandejaDeMensajesController controllerBandejaDeMensajes = new BandejaDeMensajesController();
        OperacionEgresoController controllerOperacionEgreso = new OperacionEgresoController();
        OperacionIngresoController controllerOperacionIngreso = new OperacionIngresoController();
        PresupuestoController controllerPresupuesto = new PresupuestoController();
        VinculadorController vinculadorController = new VinculadorController();
        AuthMiddleware authMiddleware       = new AuthMiddleware();

        Spark.get("/", controllerLogin::mostrarLogin, Router.engine);
        Spark.post("/iniciarSesion", controllerLogin::ingresar);
        Spark.before("/", authMiddleware::verificarSesionLogin);
        Spark.get("/cerrarSesion", controllerLogin::cerrarSesion);

        Spark.get("/operacionesEgreso", egresosController::mostrarEgresos, Router.engine);
        Spark.before("/operacionesEgreso", authMiddleware::verificarSesionGeneral);

        Spark.get("/egresosAsociados", egresosController::mostrarEgresosAsociados, Router.engine);
        Spark.before("/egresosAsociados", authMiddleware::verificarSesionGeneral);

        Spark.get("/operacionesIngreso", ingresosController::mostrarIngresos, Router.engine);
        Spark.before("/operacionesIngreso", authMiddleware::verificarSesionGeneral);

        Spark.get("/bandejaDeMensajes", controllerBandejaDeMensajes::mostrarBandejaDeMensajes, Router.engine);
        Spark.before("/bandejaDeMensajes", authMiddleware::verificarSesionGeneral);

        Spark.post("/marcarLeido", controllerBandejaDeMensajes::cambiarEstadoMensaje);
        Spark.post("/filtrarMensajes", controllerBandejaDeMensajes::filtrarMensajes, Router.engine);

        Spark.get("/egreso", controllerOperacionEgreso::nuevoEgreso, Router.engine);
        Spark.before("/egreso", authMiddleware::verificarSesionGeneral);
        Spark.post("/egreso", controllerOperacionEgreso::guardar);

        Spark.get("/egresoEditar", controllerOperacionEgreso::editarEgreso, Router.engine);
        Spark.before("/egresoEditar", authMiddleware::verificarSesionGeneral);
        Spark.post("/egresoEditar", controllerOperacionEgreso::guardarEditarEgreso);

        Spark.get("/ingreso", controllerOperacionIngreso::nuevoIngreso, Router.engine);
        Spark.before("/ingreso", authMiddleware::verificarSesionGeneral);
        Spark.post("/ingreso", controllerOperacionIngreso::guardar);

        Spark.get("/ingresoEditar", controllerOperacionIngreso::editarIngreso, Router.engine);
        Spark.before("/ingresoEditar", authMiddleware::verificarSesionGeneral);
        Spark.post("/ingresoEditar", controllerOperacionIngreso::guardarEditarIngreso);


        Spark.post("/guardarDocumento", controllerOperacionEgreso::guardarDocumentoComercial);
        Spark.post("/guardarDocumentoPresupuesto", controllerOperacionEgreso::guardarDocumentoComercialPresupuesto);

        
        Spark.get("/presupuesto", controllerPresupuesto::nuevoPresupuesto, Router.engine);
        Spark.get("/presupuestos", controllerPresupuesto::mostrarPresupuestos, Router.engine);
        Spark.get("/editarPresupuesto", controllerPresupuesto::editarPresupuesto, Router.engine);
        Spark.before("/presupuesto", authMiddleware::verificarSesionGeneral);
        Spark.before("/editarPresupuesto", authMiddleware::verificarSesionGeneral);
        Spark.before("/presupuestos", authMiddleware::verificarSesionGeneral);
        Spark.post("/presupuesto", controllerPresupuesto::guardar);

        Spark.post("/vincular", vinculadorController::ejecutarVinculador);

        
        
        
        
        
        
        
        
   

      
    }
}
