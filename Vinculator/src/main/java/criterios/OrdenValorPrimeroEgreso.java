package main.java.criterios;

import java.util.*;
import java.util.stream.Collectors;

import main.java.condiciones.PeriodoAceptabilidad;
import main.java.condiciones.PreCondicion;
import main.java.dominio.Egreso;
import main.java.dominio.Ingreso;
import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;
import main.java.dominio.RepositorioIngresosVinculados;

import com.google.gson.Gson;

public class OrdenValorPrimeroEgreso extends CriterioEjecucion {

	@Override
	public RepositorioIngresosVinculados ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos)  {

		// ordena de mayor a menor los ingresos/egresos
		List<Ingreso> ingresosOrdenados = repositorioIngresos.getIngresos()
				.stream()
				.sorted(Comparator.comparingDouble(Ingreso::getValorTotal).reversed())
				.collect(Collectors.toList());
		List<Egreso> egresosOrdenados = repositorioEgresos.getEgresos()
				.stream()
				.sorted(Comparator.comparingDouble(Egreso::getValorTotal).reversed())
				.collect(Collectors.toList());

		for(Egreso egreso : egresosOrdenados) {
			if(!egreso.isAsignado()) {
				for (Ingreso ingreso : ingresosOrdenados) {
					PreCondicion pcFechaAceptable = new PeriodoAceptabilidad(ingreso.getFecha(),ingreso.getFecha_hasta());
					if(pcFechaAceptable.cumplePrecondicion(egreso) && montoEgresoNoSuperaTotal(egreso, ingreso)) {
						ingreso.acumularMonto(egreso.getValorTotal());
						egreso.setAsignado(true);
						generarVinculacion(ingreso, egreso);
						break;
					}
				}
			}
		}
		return ingresosVinculados;
	}
}


