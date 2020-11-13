package main.java.condiciones;

import main.java.dominio.Egreso;
import main.java.dominio.RepositorioEgresos;

import java.util.List;

public interface PreCondicion {
	List<Egreso> filtrarEgresos(RepositorioEgresos repoEgresos);
	List<Egreso> filtrarEgresos(List<Egreso> egresos);
	boolean cumplePrecondicion(Egreso egreso);
}
