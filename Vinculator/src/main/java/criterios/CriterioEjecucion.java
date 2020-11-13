package main.java.criterios;

import main.java.dominio.*;

import java.util.Optional;

public abstract class CriterioEjecucion {
	protected RepositorioIngresosVinculados ingresosVinculados;

	public CriterioEjecucion(){
		this.ingresosVinculados = new RepositorioIngresosVinculados();
	}
	public CriterioEjecucion(RepositorioIngresosVinculados repoVinculaciones) { this.ingresosVinculados = repoVinculaciones; }

	public abstract RepositorioIngresosVinculados ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos);

	protected void generarVinculacion(Ingreso ingreso, Egreso egreso) {
		IngresoVinculado ingresoVinculado;
		Optional<IngresoVinculado> optionalIngresoVinculado = this.ingresosVinculados
				.getIngresosVinculados()
				.stream()
				.filter(i -> i.getId_ingreso().equals(ingreso.getId_Ingreso()))
				.findFirst();
		if(optionalIngresoVinculado.isPresent()){ //si ya existe una vinculacion previa para el ingreso
			ingresoVinculado = optionalIngresoVinculado.get();
			ingresoVinculado.agregarEgreso(egreso.getId_egreso());
		} else {
			ingresoVinculado = new IngresoVinculado(ingreso.getId_Ingreso(), ingreso.getDescripcion());
			ingresoVinculado.agregarEgreso(egreso.getId_egreso());
			this.ingresosVinculados.agregarIngresoVinculado(ingresoVinculado);
		}
	}
	protected boolean montoEgresoNoSuperaTotal(Egreso egreso, Ingreso ingreso) {
		return ingreso.getMontoAcumulado() + egreso.getValorTotal() <= ingreso.getValorTotal();
	}

	public RepositorioIngresosVinculados getIngresosVinculados() {
		return ingresosVinculados;
	}
	public void setIngresosVinculados(RepositorioIngresosVinculados ingresosVinculados) {
		this.ingresosVinculados = ingresosVinculados;
	}
}
