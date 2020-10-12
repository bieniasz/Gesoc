package domain.entities.validacionSeleccionProveedor.criteriosSeleccionProveedor;

import domain.entities.operacionComercial.*;
import domain.entities.validacionSeleccionProveedor.CriterioValidacionEgresosPresupuesto;

public class CantidadIndicada extends CriterioValidacionEgresosPresupuesto {

	public int cantidadPresupuestos;

	@Override
	public void validar (OperacionEgreso operacion) throws Exception{
		int cantidadPresupuestos = operacion.getPresupuestos().size();
		int cantidadEsperada = operacion.getCantidadEsperadaPresupuestos();

		if (cantidadPresupuestos != cantidadEsperada) {
			throw new Exception("La cantidad de presupuestos no coincide con la cantidad esperada.");
		}
	}

	public int getCantidadPresupuestos() {	return cantidadPresupuestos;}

	public void setCantidadPresupuestos(int cantidadPresupuestos) {
		this.cantidadPresupuestos = cantidadPresupuestos;
	}


}
