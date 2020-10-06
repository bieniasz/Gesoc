package main.java.dominio;

import java.util.List;

import com.google.gson.Gson;

public interface CriterioEjecucion {

	public String  ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos) ;
}
