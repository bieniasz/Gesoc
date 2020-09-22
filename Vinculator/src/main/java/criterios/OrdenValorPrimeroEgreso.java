package main.java.criterios;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import main.java.condiciones.PeriodoAceptabilidad;
import main.java.dominio.Condicion;
import main.java.dominio.CriterioEjecucion;
import main.java.dominio.Egreso;
import main.java.dominio.Ingreso;
import main.java.dominio.IngresoVinculado;
import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;

public class OrdenValorPrimeroEgreso implements CriterioEjecucion {
	
	@Override
	public String  ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos)  {

		// ordena de menor a mayor los egresos
		List<Egreso> egresosOrdenados = repositorioEgresos.getEgresos().stream()
					.sorted(Comparator.comparingDouble(Egreso::getValorTotal))
					.collect(Collectors.toList());
		
		// ordena de menor a mayor los ingresos
		List<Ingreso> ingresosOrdenados = repositorioIngresos.getIngresos().stream()
				.sorted(Comparator.comparingDouble(Ingreso::getValorTotal))
				.collect(Collectors.toList());
		
				
		
		for(  Ingreso ingreso   : ingresosOrdenados) {
			
			//filtro los egresos con la condicion dada
			List<Egreso> egrePreAsignar = new PeriodoAceptabilidad(repositorioEgresos,ingreso.getFecha()).getEgresos();
			
			Double acumulador =0.0;
			
			IngresoVinculado ingresoVinculado= new IngresoVinculado (ingreso.getId_egreso(),ingreso.getDescripcion(),ingreso.getFecha());
		    
			//asigno los egresos al ingreso hasta alcanzar un monto cercano al total
				 for(  Egreso egreso   : egrePreAsignar
				      
				      ) {
				    	
				    	if (acumulador<=ingreso.getValorTotal() && !egreso.getAsignado()) {
				    		acumulador+=egreso.getValorTotal();
				    		ingresoVinculado.agregarEgreso(egreso.getId_egreso());
				    		egreso.setAsignado(true);
				    		
				    	}
					}	
		}	// fin del for ingreso
     		
	
	return "hola";}

}


