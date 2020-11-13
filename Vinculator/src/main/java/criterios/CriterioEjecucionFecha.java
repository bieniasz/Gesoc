package main.java.criterios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.google.gson.Gson;

import main.java.condiciones.PeriodoAceptabilidad;
import main.java.condiciones.PreCondicion;
import main.java.dominio.Egreso;
import main.java.dominio.Ingreso;
import main.java.dominio.IngresoVinculado;
import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;
import main.java.dominio.RepositorioIngresosVinculados;

public class CriterioEjecucionFecha extends CriterioEjecucion {

	@Override
	public RepositorioIngresosVinculados ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos)  {
		//comparador por fechas egresos
		Comparator<Egreso> byfechaEgreso = new Comparator<Egreso>() {
			public int compare(Egreso e1, Egreso e2) {
				if (e1.getFecha().isBefore(e2.getFecha())) return -1;
				else return 1;
			}
		};
		//comparador por fechas ingresos
		Comparator<Ingreso> byfechaIngreso = new Comparator<Ingreso>() {
			public int compare(Ingreso i1, Ingreso i2) {
				if (i1.getFecha().isBefore(i2.getFecha())) return -1;
				else return 1;
			}
		};

		// ordena de menor a mayor los ingresos  por fecha
		List<Ingreso> ingresosOrdenados= repositorioIngresos.getIngresos();
		Collections.sort(ingresosOrdenados, byfechaIngreso);
		
		for(Ingreso ingreso : ingresosOrdenados) {
			PreCondicion pcFechaAceptable = new PeriodoAceptabilidad(ingreso.getFecha(), ingreso.getFecha_hasta());
			List<Egreso> egresosAceptados = pcFechaAceptable.filtrarEgresos(repositorioEgresos);
			// ordena de menor a mayor los egresos por fecha
			Collections.sort(egresosAceptados, byfechaEgreso);

			IngresoVinculado ingresoVinculado = new IngresoVinculado (ingreso.getId_Ingreso(),ingreso.getDescripcion());
			for(Egreso egreso : egresosAceptados) {
				//si el monto del egreso no supera el monto del ingreso
				if (!egreso.isAsignado() && montoEgresoNoSuperaTotal(egreso, ingreso)) {
					ingreso.acumularMonto(egreso.getValorTotal());
					egreso.setAsignado(true);
					ingresoVinculado.agregarEgreso(egreso.getId_egreso());
				}
			}

			ingresosVinculados.agregarIngresoVinculado(ingresoVinculado);
		}	// fin del for ingreso
     	
		return ingresosVinculados;
	}
}
