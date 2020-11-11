package main.java.dominio;

public class RepositorioCentral {
	public String criterioEjecucion;
	public RepositorioIngresos repositorioIngresos;
	public RepositorioEgresos repositorioEgresos;
	public RepositorioIngresos getRepositorioIngresos() {
		return repositorioIngresos;
	}
	public RepositorioEgresos getRepositorioEgresos() {
		return repositorioEgresos;
	}
}
