package main.java.criterios;

import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;
import main.java.dominio.RepositorioIngresosVinculados;
import main.java.factories.CriterioEjecucionFactory;

import java.util.ArrayList;
import java.util.List;

public class CriterioMix extends CriterioEjecucion {
    List<String> strCriteriosAEjecutar = new ArrayList<>();

    @Override
    public RepositorioIngresosVinculados ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos) {
        for(String strNombreSubCriterio : strCriteriosAEjecutar) {
            CriterioEjecucion subCriterio = CriterioEjecucionFactory.getCriterioEjecucion(strNombreSubCriterio);
            subCriterio.setIngresosVinculados(this.ingresosVinculados);
            subCriterio.ejecutar(repositorioIngresos, repositorioEgresos);
        }
        return this.ingresosVinculados;
    }

    public void agregarCriterio(String criterio) {
        this.strCriteriosAEjecutar.add(criterio);
    }
    public void removerCriterio(String criterio) {
        this.strCriteriosAEjecutar.remove(criterio);
    }
}
