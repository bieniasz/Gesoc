package main.java.server;

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

        Spark.get("/saludo", prueba::saludar);
    }
}

class PruebaRouter {
    public String saludar(Request request, Response response){
        return "hola posta";
    }
}

