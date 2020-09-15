package validacionEgresos;

import java.util.ArrayList;

import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;

public abstract class CriterioValidacionEgresosPresupuesto{

	public abstract void validar (OperacionEgreso operacion) throws Exception;
}
