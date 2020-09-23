package main.java.server;

import main.java.dominio.VinculadorOperaciones;
import spark.Request;
import spark.Response;
import spark.Spark;

public class Router {

    public static void init() {
        Spark.staticFileLocation("/public");
        Router.configure();
    }

    private static void configure(){
        VinculadorOperaciones vinculador = new VinculadorOperaciones();

        Spark.get("/saludo", vinculador::vincular);
    }
}