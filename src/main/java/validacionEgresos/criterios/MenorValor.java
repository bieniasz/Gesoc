package validacionEgresos.criterios;

import java.util.ArrayList;
import java.util.Comparator;

import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;
import validacionEgresos.CriterioValidacionEgresosPresupuesto;

public class MenorValor implements CriterioValidacionEgresosPresupuesto {

	@Override
	public  void validar(OperacionEgreso operacion) throws Exception{
		//ordena de menor a mayor, por eso traigo el primero
		Presupuesto menorValor = operacion.getPresupuestos().stream().sorted(Comparator.comparing(t->t.getValorTotal())).findFirst().get();
		Presupuesto elegido= operacion.getPresupuestos().stream().filter(presu->presu.isEsElElegido()).findFirst().get();
		if (menorValor!= elegido) {
			throw new Exception("No coincide el presupuesto elegido con el de menor valor");
		};	
		
	}

}
