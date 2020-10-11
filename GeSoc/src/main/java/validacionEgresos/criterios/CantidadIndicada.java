package validacionEgresos.criterios;

import java.util.ArrayList;

import operacionComercial.*;
import operacionComercial.Presupuesto;
import validacionEgresos.CriterioValidacionEgresosPresupuesto;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CantidadIndicada")
public class CantidadIndicada extends CriterioValidacionEgresosPresupuesto {

	@Column
	public int cantidadPresupuestos;

	@Override
	public void validar  (OperacionEgreso operacion) throws Exception{
		int cantidad =operacion.getPresupuestos().size();
		if (cantidad < this.cantidadPresupuestos) {
			throw new Exception("No coincide con la cantidad indicada de presupuestos");
		}
	}

	public int getCantidadPresupuestos() {	return cantidadPresupuestos;}

	public void setCantidadPresupuestos(int cantidadPresupuestos) {
		this.cantidadPresupuestos = cantidadPresupuestos;
	}


}
