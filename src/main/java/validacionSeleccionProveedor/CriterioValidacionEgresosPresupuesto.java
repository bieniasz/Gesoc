package validacionSeleccionProveedor;

import operacionComercial.OperacionEgreso;
import operacionComercial.Presupuesto;

public abstract class CriterioValidacionEgresosPresupuesto{

	public abstract void validar (OperacionEgreso operacion ) throws Exception;

	public Presupuesto obtenerPresupuestoElegido(OperacionEgreso operacionEgreso) {
		Presupuesto pElegido = operacionEgreso
				.getPresupuestos()
				.stream()
				.filter(Presupuesto::isElegido)
				.findFirst()
				.get();

		return pElegido;
	}
}
