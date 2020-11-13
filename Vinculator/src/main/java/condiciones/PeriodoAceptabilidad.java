package main.java.condiciones;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import main.java.dominio.Egreso;
import main.java.dominio.RepositorioEgresos;

public class PeriodoAceptabilidad implements PreCondicion {
	
	private RepositorioEgresos repositorioEgresos;
	private LocalDate fecha_hasta;
	private LocalDate fecha_desde;

	public PeriodoAceptabilidad(LocalDate fecha_desde, LocalDate fecha_hasta) {
		this.fecha_desde=fecha_desde;
		this.fecha_hasta=fecha_hasta;
	}

	@Override
	public List<Egreso> filtrarEgresos(RepositorioEgresos repoEgresos) {
		return repoEgresos.getEgresos()
				.stream()
				.filter(eg -> egresoEstaEntreFechas(eg, this.fecha_desde, this.fecha_hasta))
				.collect(Collectors.toList());
	}

	@Override
	public List<Egreso> filtrarEgresos(List<Egreso> egresos) {
		return egresos
				.stream()
				.filter(eg -> egresoEstaEntreFechas(eg, this.fecha_desde, this.fecha_hasta))
				.collect(Collectors.toList());
	}

	@Override
	public boolean cumplePrecondicion(Egreso egreso) {
		return egresoEstaEntreFechas(egreso, this.fecha_desde, this.fecha_hasta);
	}

	private boolean egresoEstaEntreFechas(Egreso egreso, LocalDate desde, LocalDate hasta) {
		LocalDate fechaEgreso = egreso.getFecha();
		return fechaEgreso.compareTo(desde) >= 0 && fechaEgreso.compareTo(hasta) <= 0;
	}

	public LocalDate getFecha_hasta() {
		return fecha_hasta;
	}
	public void setFecha_hasta(LocalDate fecha_hasta) {
		this.fecha_hasta = fecha_hasta;
	}
	public RepositorioEgresos getRepositorioEgresos() {
		return repositorioEgresos;
	}
	public void setRepositorioEgresos(RepositorioEgresos repositorioEgresos) {
		this.repositorioEgresos = repositorioEgresos;
	}
	public LocalDate getFecha_desde() {
		return fecha_desde;
	}
	public void setFecha_desde(LocalDate fecha_desde) {
		this.fecha_desde = fecha_desde;
	};
}
