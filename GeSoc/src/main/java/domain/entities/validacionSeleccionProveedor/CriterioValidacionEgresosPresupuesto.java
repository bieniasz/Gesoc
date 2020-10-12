package domain.entities.validacionSeleccionProveedor;

import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.Presupuesto;

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
