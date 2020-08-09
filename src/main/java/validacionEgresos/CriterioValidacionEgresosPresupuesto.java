package validacionEgresos;

import java.util.ArrayList;

import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;

public interface CriterioValidacionEgresosPresupuesto{

	public void validar (OperacionEgreso operacion ) throws Exception;

}
