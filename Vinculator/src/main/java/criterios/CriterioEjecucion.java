package main.java.criterios;

import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;

public interface CriterioEjecucion {
	String ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos);
}
