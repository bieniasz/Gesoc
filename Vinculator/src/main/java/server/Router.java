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
        VinculadorOperaciones vinculadorOperaciones = new VinculadorOperaciones();


        Spark.get("/saludo", ( request, response) -> "Alo");
        Spark.put("/vincular", vinculadorOperaciones::vincular);

    }
}