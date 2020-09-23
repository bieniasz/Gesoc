package test.java;

import com.google.gson.Gson;
import main.java.dominio.RepositorioCentral;
import org.junit.Test;

public class ParseJsonTest {

    @Test
    public void lala(){
        String repo = "{" +
                "\"repositorioEgresos\": {" +
                "\"egresos\":[" +
                "{" +
                "\"id_egreso\":\"123123412\"," +
                "\"fecha\":\"2020-02-01\"," +
                "\"valorTotal\": 12.5," +
                "\"documentoComercial\":\"213123123\"," +
                "\"detalle\" :\"media docena facturas\"," +
                "}" +
		"]" +
        "}," +

                "\"repositorioIgresos\":{" +
            "\"ingresos\":[" +
        "{" +
        "\"id_ingreso\":\"5445345\"," +
        "\"fecha\":\"2020-09-20\"," +
        "\"valorTotal\":\"5654654\"," +
        "\"descripcion\":\"una donacion\"" +
        "}"+
        "]"+
        "}" +
        "}";

        Gson gsonCentral = new Gson();
        RepositorioCentral repositorioCentral = gsonCentral.fromJson(repo, RepositorioCentral.class);
    }
}
