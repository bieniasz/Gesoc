package main.java.dominio;

import java.util.ArrayList;
import java.util.List;

public class RepositorioIngresosVinculados {
	List <IngresoVinculado> ingresosVinculados= new ArrayList<> ();
	
	public void agregarIngresoVinculado(IngresoVinculado ig) {
		ingresosVinculados.add(ig);
	}

	public List<IngresoVinculado> getIngresosVinculados() {
		return ingresosVinculados;
	}

	public void setIngresosVinculados(List<IngresoVinculado> ingresosVinculados) {
		this.ingresosVinculados = ingresosVinculados;
	}
}
