package validacionEgresos.criterios;

import java.util.ArrayList;

import operacionComercial.*;
import operacionComercial.Presupuesto;
import validacionEgresos.CriterioValidacionEgresosPresupuesto;

public class CantidadIndicada implements CriterioValidacionEgresosPresupuesto {

	@Override
	public Presupuesto validar(OperacionEgreso operacion, ArrayList<Presupuesto> Presupuestos) {
		
		Presupuesto resultado;
	
		
		 //operacion.getDetalle().stream().forEach(det->det.getItem()==resultado );
		
		resultado = Presupuestos.stream().filter(presu -> presu.equals(presu.getListaItemCantidad(),operacion.getListaItemCantidad()))
		.findFirst().get();
		
		return resultado ;
	}

	public  void corteControlItem(){
	
		
	};
}
