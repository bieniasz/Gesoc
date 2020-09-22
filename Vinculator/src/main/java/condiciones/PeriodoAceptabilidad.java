package main.java.condiciones;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import main.java.dominio.Condicion;
import main.java.dominio.Egreso;
import main.java.dominio.RepositorioEgresos;

public class PeriodoAceptabilidad extends Condicion {
	
	private RepositorioEgresos repositorioEgresos;
	private LocalDate fecha_hasta;

	
	
	public PeriodoAceptabilidad(RepositorioEgresos repositorioEgresos, LocalDate fecha) {
		this.fecha_hasta=fecha;
		this.repositorioEgresos=repositorioEgresos;
	}

	@Override
	public List<Egreso> getEgresos() {
		
		return repositorioEgresos.getEgresos().stream().
	    filter(e->e.getFecha().isAfter(fecha_hasta)&& e.getFecha().isBefore(fecha_hasta)).collect(Collectors.toList());

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
	};

}
