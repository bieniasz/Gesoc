package domain.entities.validacionSeleccionProveedor.criteriosSeleccionProveedor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import domain.entities.operacionComercial.OperacionComercial;
import domain.entities.operacionComercial.OperacionEgreso;
import domain.entities.operacionComercial.Presupuesto;
import domain.entities.validacionSeleccionProveedor.CriterioValidacionEgresosPresupuesto;

public class MenorValor extends CriterioValidacionEgresosPresupuesto {

	@Override
	public void validar(OperacionEgreso operacion) throws Exception {
		List<Presupuesto> lstPresupuestosMinimos = getPresupuestosMenorValor(operacion);
		Presupuesto elegido = super.obtenerPresupuestoElegido(operacion);

		if ((elegido == null) || !lstPresupuestosMinimos.contains(elegido))
			throw new Exception("El presupuesto elegido no coincide con el/los de menor valor");
	}

	private List<Presupuesto> getPresupuestosMenorValor(OperacionEgreso op) {
		List<Presupuesto> lstPresupuestosMinimos = new ArrayList<Presupuesto>();

		Presupuesto presupuestoMenorValor = op
				.getPresupuestos()
				.stream()
				.min(Comparator.comparing(OperacionComercial::getValorTotal))
				.get();

		//obtengo todos los presupuestos con el mismo valorTotal minimo
		lstPresupuestosMinimos = op.getPresupuestos()
				.stream()
				.filter(p -> p.getValorTotal().equals(presupuestoMenorValor.getValorTotal()))
				.collect(Collectors.toList());

		return lstPresupuestosMinimos;
	}

}
