package main.java.factories;

import main.java.criterios.*;

public class CriterioEjecucionFactory {
    public static CriterioEjecucion getCriterioEjecucion(String nombre) {
        CriterioEjecucion criterio;
        switch (nombre) {
            case "Orden-Valor-PrimeroEgreso":
                criterio = new OrdenValorPrimeroEgreso();
                break;
            case "Fecha":
                criterio = new CriterioEjecucionFecha();
                break;
            //TODO case "Orden-Valor-PrimeroIngreso"
            //TODO case "Mix"
            default:
                criterio = null;
        }
        return criterio;
    }
}
