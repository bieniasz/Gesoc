package main.java.dominio;

import java.util.List;

public class RepositorioCentral {
	public String criterioEjecucion;
	public List<String> criteriosMix;
	public RepositorioIngresos repositorioIngresos;
	public RepositorioEgresos repositorioEgresos;
	public RepositorioIngresos getRepositorioIngresos() {
		return repositorioIngresos;
	}
	public RepositorioEgresos getRepositorioEgresos() {
		return repositorioEgresos;
	}
}
