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
        PruebaRouter prueba = new PruebaRouter();
        VinculadorOperaciones vinculadorOperaciones = new VinculadorOperaciones();
        
        Spark.get("/saludo", prueba::saludar);
        Spark.put("/vincular", vinculadorOperaciones::vincular);
    }
}

class PruebaRouter {
    public String saludar(Request request, Response response){
        return "hola posta";
    }
}

