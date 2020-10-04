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
	private LocalDate fecha_desde;
	
	
	public PeriodoAceptabilidad(RepositorioEgresos repositorioEgresos, LocalDate fecha_desde, LocalDate fecha_hasta) {
		this.fecha_desde=fecha_desde;
		this.fecha_hasta=fecha_hasta;
		this.repositorioEgresos=repositorioEgresos;
	}

	@Override
	public List<Egreso> getEgresos() {
		
		return repositorioEgresos.getEgresos().stream().
	    filter(e->e.getFecha().isAfter(fecha_desde)&& e.getFecha().isBefore(fecha_hasta)).collect(Collectors.toList());

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
