package main.java.criterios;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.google.gson.Gson;

import main.java.condiciones.PeriodoAceptabilidad;
import main.java.dominio.CriterioEjecucion;
import main.java.dominio.Egreso;
import main.java.dominio.Ingreso;
import main.java.dominio.IngresoVinculado;
import main.java.dominio.RepositorioEgresos;
import main.java.dominio.RepositorioIngresos;
import main.java.dominio.RepositorioIngresosVinculados;

public class CriterioEjecucionFecha implements CriterioEjecucion {

	@Override
	public String  ejecutar(RepositorioIngresos repositorioIngresos, RepositorioEgresos repositorioEgresos)  {

		RepositorioIngresosVinculados	ingresosVinculados = new RepositorioIngresosVinculados();
		
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
		
		
				
		
		for(  Ingreso ingreso   : ingresosOrdenados) {
			
			//filtro los egresos con la condicion dada
			List<Egreso> egrePreAsignar = new PeriodoAceptabilidad(repositorioEgresos,ingreso.getFecha(),ingreso.getFecha_hasta()).getEgresos();
			// ordena de menor a mayor los egresos por fecha
			Collections.sort(egrePreAsignar, byfechaEgreso);	
			
			Double acumulador =0.0;
			
			IngresoVinculado ingresoVinculado = new IngresoVinculado (ingreso.getId_Ingreso(),ingreso.getDescripcion(),ingreso.getFecha());
		    
			//asigno los egresos al ingreso hasta alcanzar un monto cercano al total
				 for(  Egreso egreso   : egrePreAsignar
				      
				      ) {
				    	
				    	if (acumulador<=ingreso.getValorTotal() && !egreso.getAsignado()) {
				    		acumulador+=egreso.getValorTotal();
				    		ingresoVinculado.agregarEgreso(egreso.getId_egreso());
				    		egreso.setAsignado(true);
				    		
				    	}
					}	
		ingresosVinculados.agregarIngresoVinculado(ingresoVinculado);
		}	// fin del for ingreso
     	
		Gson gson = new Gson();
	String ingresosVinculadoString = gson.toJson(ingresosVinculados);
	
	return ingresosVinculadoString;}

}
