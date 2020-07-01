package validacionEgresos;

import java.util.ArrayList;

import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;

public interface CriterioValidacionEgresosPresupuesto {

	public Presupuesto validar (OperacionEgreso operacion, ArrayList <Presupuesto> Presupuestos );

}
